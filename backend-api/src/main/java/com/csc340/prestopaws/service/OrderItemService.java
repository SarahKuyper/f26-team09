package com.csc340.prestopaws.service;

import com.csc340.prestopaws.DTO.OrderItemRequest;
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Order;
import com.csc340.prestopaws.entity.OrderItem;
import com.csc340.prestopaws.repository.ItemRepository;
import com.csc340.prestopaws.repository.OrderItemRepository;
import com.csc340.prestopaws.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderRepository orderRepository,
                            ItemRepository itemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public OrderItem createOrderItem(Long orderId, OrderItemRequest request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
        if (item.getPrice() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item has no price");
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setUnitPrice(item.getPrice());
        orderItem.setQuantity(request.getQuantity());
        order.getOrderItems().add(orderItem);
        order.setSubtotal(order.getOrderItems().stream()
                .mapToDouble(existing -> existing.getUnitPrice() * existing.getQuantity())
                .sum());
        order.setTotal(order.getSubtotal() + order.getShippingPrice());

        orderRepository.save(order);
        return orderItem;
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order item not found"));
    }

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public List<OrderItem> getOrderItemsForOrder(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return orderItemRepository.findByOrderId(orderId);
    }
}
