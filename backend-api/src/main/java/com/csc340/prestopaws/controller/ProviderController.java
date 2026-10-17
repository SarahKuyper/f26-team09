package com.csc340.prestopaws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

// Plus your project-specific imports (adjust paths if needed):
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;
import com.csc340.prestopaws.service.ItemService;
import com.csc340.prestopaws.service.ProviderService;
import com.csc340.prestopaws.DTO.ItemRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/providers")
public class ProviderController {
    private final ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // POST /api/providers
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Provider createProvider(@Valid @RequestBody Provider provider) {
        return providerService.createProvider(provider);
    }

    // GET /api/providers/{id}
    @GetMapping("/{id}")
    public Provider getProvider(@PathVariable Long id) {
        return providerService.getProviderById(id);
    }
}
