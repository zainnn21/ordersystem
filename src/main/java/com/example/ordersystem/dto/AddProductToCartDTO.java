package com.example.ordersystem.dto;

import lombok.Data;

@Data
public class AddProductToCartDTO {
    private Long productId;
    private int quantity;
}
