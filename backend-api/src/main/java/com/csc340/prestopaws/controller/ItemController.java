package com.csc340.prestopaws.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.service.ItemService;
import com.csc340.prestopaws.DTO.ItemRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/items")
public class ItemController {
    // ... rest of your code
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