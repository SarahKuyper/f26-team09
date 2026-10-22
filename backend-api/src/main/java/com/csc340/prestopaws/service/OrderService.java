package com.csc340.prestopaws.service;

import com.csc340.prestopaws.DTO.OrderItemRequest;
import com.csc340.prestopaws.DTO.OrderRequest;
import com.csc340.prestopaws.entity.Buyer;
import com.csc340.prestopaws.entity.Item;
import com.csc340.prestopaws.entity.Order;
import com.csc340.prestopaws.entity.OrderItem;
import com.csc340.prestopaws.entity.OrderStatus;
import com.csc340.prestopaws.repository.BuyerRepository;
import com.csc340.prestopaws.repository.ItemRepository;
import com.csc340.prestopaws.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final BuyerRepository buyerRepository;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, BuyerRepository buyerRepository,
                       ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.buyerRepository = buyerRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public Order createOrder(OrderRequest request) {
        Buyer buyer = buyerRepository.findById(request.getBuyerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buyer not found"));

        Order order = new Order();
        order.setBuyer(buyer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setShippingPrice(request.getShippingPrice());

        double subtotal = 0.0;
        for (OrderItemRequest itemRequest : request.getOrderItems()) {
            Item item = itemRepository.findById(itemRequest.getItemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Item not found: " + itemRequest.getItemId()));
            if (item.getPrice() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Item has no price: " + itemRequest.getItemId());
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setOrder(order);
            orderItem.setUnitPrice(item.getPrice());
            orderItem.setQuantity(itemRequest.getQuantity());
            order.getOrderItems().add(orderItem);
            subtotal += item.getPrice() * itemRequest.getQuantity();
        }

        order.setSubtotal(subtotal);
        order.setTotal(subtotal + request.getShippingPrice());
        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found"));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersForBuyer(Long buyerId) {
        if (!buyerRepository.existsById(buyerId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Buyer not found");
        }
        return orderRepository.findByBuyerId(buyerId);
    }
}
