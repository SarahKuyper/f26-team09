package com.csc340.prestopaws.service;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.Optional;

// Project-specific:
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Provider;
import com.csc340.prestopaws.repository.ItemRepository;
import com.csc340.prestopaws.repository.ProviderRepository;
import com.csc340.prestopaws.DTO.ItemRequest;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final ProviderRepository providerRepository;

    public ItemService(ItemRepository itemRepository, ProviderRepository providerRepository) {
        this.itemRepository = itemRepository;
        this.providerRepository = providerRepository;
    }

    public Item createItem(ItemRequest request) {
        // 1. Find the provider
        Provider provider = providerRepository.findById(request.getProviderId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provider not found"));

        // 2. Map DTO to Entity
        Item item = new Item();
        item.setName(request.getName());
        item.setDescription(request.getDescription());
        item.setPrice(request.getPrice());
        item.setStockQuantity(request.getStockQuantity());
        item.setCategory(request.getCategory());
        item.setIsAvailable(request.getIsAvailable());
        item.setImageUrl(request.getImageUrl());
        item.setProvider(provider);

        // 3. Save
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByProviderId(Long providerId) {
        return itemRepository.findByProviderId(providerId);
    }

    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        itemRepository.deleteById(id);
    }

    public Item updateItem(Long id, ItemRequest request) {
        Item existing = itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));

        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setStockQuantity(request.getStockQuantity());
        existing.setCategory(request.getCategory());
        existing.setIsAvailable(request.getIsAvailable());

        if (request.getImageUrl() == null || request.getImageUrl().trim().isEmpty()) {
            existing.setImageUrl("/Images/NoImage.jpg");
        } else {
            existing.setImageUrl(request.getImageUrl());
        }

        return itemRepository.save(existing);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
    }
}