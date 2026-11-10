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

        try {
            Item saved = itemService.createItem(itemRequest);
            return "redirect:/providers/" + providerId + "/items";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", e.getReason());
            return "error";
        }
    }
}