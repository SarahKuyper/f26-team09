package com.csc340.prestopaws.service;

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
        item.setProvider(provider);

        // 3. Save
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}