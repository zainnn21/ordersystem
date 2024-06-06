package com.example.ordersystem.dto;

import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
public class ProductDTO {
    private Long id; // Product ID
    private String name; // Name of the product
    private String type; // Type of the product
    private Double price; // Price of the product
    private Integer quantity; // Quantity of the product
}
