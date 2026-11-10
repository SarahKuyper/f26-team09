package com.csc340.prestopaws.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;

// Project-specific:
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;
import com.csc340.prestopaws.repository.ItemRepository;
import com.csc340.prestopaws.repository.ProviderRepository;
import com.csc340.prestopaws.DTO.ItemRequest;

@Service
public class ProviderService {
    private final ProviderRepository providerRepository;

    public ProviderService(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    public Provider createProvider(Provider provider) {
        return providerRepository.save(provider);
    }

    public Provider getProviderById(Long id) {
        return providerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider not found"));
    }

    public List<Provider> getAllProviders() {
        return providerRepository.findAll();
    }

    public Provider updateProvider(Long id, Provider updated) {
        Provider existing = getProviderById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        // Only update password if a new one was provided
        if (updated.getPassword() != null && !updated.getPassword().isBlank()) {
            existing.setPassword(updated.getPassword());
        }
        return providerRepository.save(existing);
    }

    public void deleteProvider(Long id) {
        if (!providerRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider not found");
        }
        providerRepository.deleteById(id);
    }
}