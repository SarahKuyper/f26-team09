package com.csc340.prestopaws.controller;

import com.csc340.prestopaws.DTO.OrderRequest;
import com.csc340.prestopaws.entity.Order;
import com.csc340.prestopaws.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customers/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping("/buyer/{buyerId}")
    public List<Order> getOrdersForBuyer(@PathVariable Long buyerId) {
        return orderService.getOrdersForBuyer(buyerId);
    }
}
