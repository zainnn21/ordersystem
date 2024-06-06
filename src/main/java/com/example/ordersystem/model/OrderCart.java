package com.example.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table.
@Data // Lombok generates getters, setters, equals, hash, and toString methods
public class OrderCart {
    @Id // This annotation specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    private String customer; // Customer name
    private String address; // Customer address

    @ElementCollection // This annotation is used to define a collection of basic or embeddable types.
    private List<OrderItem> items; // List of items in the order cart

    @Transient // This annotation specifies that the field is not persistent.
    private Double total; // Total price of the order cart

    // Calculates the total price for all items in the cart.
    public Double getTotal(){
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }
}
