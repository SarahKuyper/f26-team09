package com.csc340.prestopaws.controller;

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
