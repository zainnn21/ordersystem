package com.example.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data // Lombok generates getters, setters, equals, hash, and toString methods
public class OrderCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    private String customer;
    private String address;

    @ElementCollection // Represents a collection of elements (in this case, OrderItems)
    private List<OrderItem> items;

    @Transient // this field is not stored in the database
    private Double total;

    //Calculate total price for the order
    public Double getTotal(){
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }
}
