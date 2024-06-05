package com.example.ordersystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // Using Lombok to automatically generate getters, setters, equals, hash, and toString methods
public class Product {
    @Id // Auto-increment ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Double price;
    private Integer quantity;
}
