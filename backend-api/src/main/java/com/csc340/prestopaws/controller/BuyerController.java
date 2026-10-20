package com.csc340.prestopaws.controller;

import com.csc340.prestopaws.entity.Buyer;
import com.csc340.prestopaws.service.BuyerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Buyer createBuyer(@Valid @RequestBody Buyer buyer) {
        return buyerService.createBuyer(buyer);
    }

    @GetMapping
    public List<Buyer> getAllBuyers() {
        return buyerService.getAllBuyers();
    }

    @GetMapping("/{id}")
    public Buyer getBuyer(@PathVariable Long id) {
        return buyerService.getBuyerById(id);
    }
}
