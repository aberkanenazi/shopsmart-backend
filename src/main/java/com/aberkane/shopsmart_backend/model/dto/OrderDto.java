package com.aberkane.shopsmart_backend.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.aberkane.shopsmart_backend.model.OrderStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    @NotNull
    private Long orderId;

    @NotNull
    private Long userId;

    @NotNull
    private Double totalPrice;

    @NotNull
    @Size(max = 3)
    private String currency;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}