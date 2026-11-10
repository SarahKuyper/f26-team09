package com.csc340.prestopaws.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ItemRequest {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Price must be a positive value")
    private Double price;

    private Integer stockQuantity;
    private String category;
    private Boolean isAvailable;
    private String imageUrl;
    private Long providerId;
}