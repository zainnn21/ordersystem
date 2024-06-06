package com.example.ordersystem.dto;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
public class OrderItemDTO {
    private String productName; // Name of the product
    private String productType; // Type of the product
    private Double productPrice; // Price of the product
    private Integer quantity; // Quantity of the product
    private Double total; // Total price for the quantity of products
}
