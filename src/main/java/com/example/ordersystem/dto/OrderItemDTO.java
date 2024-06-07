package com.example.ordersystem.dto;

import com.example.ordersystem.model.OrderItem;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
public class OrderItemDTO {
    private Long id;
    private String productName; // Name of the product
    private String productType; // Type of the product
    private double price; // Price of the product
    private int quantity; // Quantity of the product
    private double totalPrice; // Total price for the quantity of products

    public OrderItemDTO(OrderItem orderItem){
        this.id = orderItem.getId();
        this.productName = orderItem.getProduct().getName();
        this.productType = orderItem.getProduct().getType();
        this.price = orderItem.getProduct().getPrice();
        this.quantity = orderItem.getQuantity();
        this.totalPrice = orderItem.getTotalPrice();
    }
}
