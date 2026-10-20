package com.csc340.prestopaws.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemRequest {

    @NotNull
    private Long itemId;

    @NotNull
    @Positive
    private Integer quantity;
}
