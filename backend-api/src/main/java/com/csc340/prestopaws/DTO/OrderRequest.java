package com.csc340.prestopaws.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderRequest {

    @NotNull
    private Long buyerId;

    @NotNull
    @PositiveOrZero
    private Double shippingPrice;

    @NotEmpty
    private List<@Valid OrderItemRequest> orderItems;
}
