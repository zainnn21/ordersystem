package com.example.ordersystem.dto;

import com.example.ordersystem.model.OrderCart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
public class OrderCartDTO {
    private Long id; // Order Cart ID
    private String customer; // Customer name
    private String address; // Customer address
    private List<OrderItemDTO> items; // List of items in the order cart
    private double totalPrice; // Total price for all items in the cart

    public OrderCartDTO(OrderCart orderCart) {
        this.id = orderCart.getId();
        this.customer = orderCart.getCustomer();
        this.address = orderCart.getAddress();
        this.items = orderCart.getItems() != null ? orderCart.getItems().stream().map(OrderItemDTO::new).collect(Collectors.toList()) : new ArrayList<>();
        this.totalPrice = orderCart.getTotalPrice();
    }
}
