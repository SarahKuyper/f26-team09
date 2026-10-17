package com.csc340.prestopaws.DTO;

import lombok.Data;

@Data
public class ItemRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private String category;
    private Boolean isAvailable;
    private Long providerId; // Client sends this ID
}
