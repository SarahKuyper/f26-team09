package com.csc340.prestopaws.controller;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // POST /api/items (Provider creates a service/item)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Item createItem(@Valid @RequestBody ItemRequest request) {
        return itemService.createItem(request);
    }

    // GET /api/items (View available services)
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}