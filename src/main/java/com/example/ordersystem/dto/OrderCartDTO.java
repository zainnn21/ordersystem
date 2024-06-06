package com.example.ordersystem.dto;

import lombok.Data;

import java.util.List;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
public class OrderCartDTO {
    private Long id; // Order Cart ID
    private String customer; // Customer name
    private String address; // Customer address
    private List<OrderItemDTO> items; // List of items in the order cart
    private Double total; // Total price of the order cart
}
