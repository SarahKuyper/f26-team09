package com.csc340.prestopaws.controller;

import com.csc340.prestopaws.DTO.ItemRequest;
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;
import com.csc340.prestopaws.service.ItemService;
import com.csc340.prestopaws.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/providers/{providerId}/items")
public class ItemMvcController {

    private final ItemService itemService;
    private final ProviderService providerService;

    public ItemMvcController(ItemService itemService, ProviderService providerService) {
        this.itemService = itemService;
        this.providerService = providerService;
    }

    @GetMapping
    public String list(@PathVariable Long providerId, Model model) {
        Provider provider;
        try {
            provider = providerService.getProviderById(providerId);
        } catch (ResponseStatusException e) {
            model.addAttribute("message", "No provider with id " + providerId);
            return "error";
        }
        model.addAttribute("provider", provider);
        model.addAttribute("items", itemService.getItemsByProviderId(providerId));
        return "item/list";
    }

    @GetMapping("/new")
    public String createForm(@PathVariable Long providerId, Model model) {
        try {
            providerService.getProviderById(providerId);
        } catch (ResponseStatusException e) {
            model.addAttribute("message", "No provider with id " + providerId);
            return "error";
        }
        
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setProviderId(providerId);
        itemRequest.setIsAvailable(true);
        
        // REMOVED: itemRequest.setImageUrl("/Images/NoImage.jpg");

        model.addAttribute("item", itemRequest);
        model.addAttribute("providerId", providerId);
        return "item/form";
    }

    @PostMapping
    public String create(@PathVariable Long providerId,
            @Valid @ModelAttribute("item") ItemRequest itemRequest,
            BindingResult result, Model model) {

        itemRequest.setProviderId(providerId);

        if (result.hasErrors()) {
            model.addAttribute("providerId", providerId);
            model.addAttribute("errors", result);
            return "item/form";
        }

        // Enforce the default if the user manually cleared the box
        if (itemRequest.getImageUrl() == null || itemRequest.getImageUrl().trim().isEmpty()) {
            itemRequest.setImageUrl("/Images/NoImage.jpg");
        }

        try {
            itemService.createItem(itemRequest);
            return "redirect:/providers/" + providerId + "/items";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", e.getReason());
            return "error";
        }
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long providerId, @PathVariable Long itemId, Model model) {
        try {
            Provider provider = providerService.getProviderById(providerId);
            Item item = itemService.getItemById(itemId);

            if (!item.getProvider().getId().equals(provider.getId())) {
                model.addAttribute("message", "Item does not belong to the specified provider");
                return "error";
            }

            ItemRequest itemRequest = new ItemRequest();
            itemRequest.setName(item.getName());
            itemRequest.setDescription(item.getDescription());
            itemRequest.setPrice(item.getPrice());
            itemRequest.setStockQuantity(item.getStockQuantity());
            itemRequest.setCategory(item.getCategory());
            itemRequest.setIsAvailable(item.getIsAvailable());
            itemRequest.setImageUrl(item.getImageUrl());
            itemRequest.setProviderId(providerId);

            model.addAttribute("item", itemRequest);
            model.addAttribute("providerId", providerId);
            model.addAttribute("itemId", itemId);
            return "item/form";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", e.getReason());
            return "error";
        }
    }

        @PostMapping("/{itemId}/edit")
    public String update(@PathVariable Long providerId, @PathVariable Long itemId,
            @Valid @ModelAttribute("item") ItemRequest itemRequest,
            BindingResult result, Model model) {

        itemRequest.setProviderId(providerId);

        if (result.hasErrors()) {
            model.addAttribute("providerId", providerId);
            model.addAttribute("itemId", itemId);
            model.addAttribute("errors", result);
            return "item/form"; // Re-use the same form template
        }

        try {
            itemService.updateItem(itemId, itemRequest);
            return "redirect:/providers/" + providerId + "/items";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", e.getReason());
            return "error";
        }
    }
}