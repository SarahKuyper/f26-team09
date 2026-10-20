package com.csc340.prestopaws.controller;

import com.csc340.prestopaws.DTO.OrderItemRequest;
import com.csc340.prestopaws.entity.OrderItem;
import com.csc340.prestopaws.service.OrderItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customers/order-items")
public class OrderItemController {

    private final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("/order/{orderId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderItem createOrderItem(@PathVariable Long orderId,
                                     @Valid @RequestBody OrderItemRequest request) {
        return orderItemService.createOrderItem(orderId, request);
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems() {
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItem getOrderItem(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @GetMapping("/order/{orderId}")
    public List<OrderItem> getOrderItemsForOrder(@PathVariable Long orderId) {
        return orderItemService.getOrderItemsForOrder(orderId);
    }
}
