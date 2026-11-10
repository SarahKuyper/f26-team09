package com.csc340.prestopaws.controller;

import com.csc340.prestopaws.entity.Provider;
import com.csc340.prestopaws.service.ProviderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/providers")
public class ProviderMvcController {

    private final ProviderService providerService;

    public ProviderMvcController(ProviderService providerService) {
        this.providerService = providerService;
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("provider", new Provider());
        model.addAttribute("editing", false);
        return "provider/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("provider") Provider provider,
            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("editing", false);
            model.addAttribute("errors", result);
            return "provider/form";
        }
        Provider saved = providerService.createProvider(provider);
        return "redirect:/providers/" + saved.getId();
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("provider", providerService.getProviderById(id));
            return "provider/profile";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", "No provider with id " + id);
            return "error";
        }
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("provider", providerService.getProviderById(id));
            model.addAttribute("editing", true);
            return "provider/form";
        } catch (ResponseStatusException e) {
            model.addAttribute("message", "No provider with id " + id);
            return "error";
        }
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
            @ModelAttribute("provider") Provider provider,
            BindingResult result, Model model) {

        // Manual validation for name and email only
        if (provider.getName() == null || provider.getName().isBlank()) {
            result.rejectValue("name", "required", "Name is required");
        }
        if (provider.getEmail() == null || provider.getEmail().isBlank()) {
            result.rejectValue("email", "required", "Email is required");
        } else if (!provider.getEmail().matches("^[^@]+@[^@]+\\.[^@]+$")) {
            result.rejectValue("email", "email", "Email should be valid");
        }

        if (result.hasErrors()) {
            provider.setId(id);
            model.addAttribute("editing", true);
            model.addAttribute("errors", result);
            return "provider/form";
        }

        try {
            providerService.updateProvider(id, provider);
            return "redirect:/providers/" + id;
        } catch (ResponseStatusException e) {
            model.addAttribute("message", "No provider with id " + id);
            return "error";
        }
    }
}