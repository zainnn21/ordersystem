package com.example.ordersystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table.
@Data // Using Lombok to automatically generate getters, setters, equals, hash, and toString methods
@NoArgsConstructor
public class Product {
    @Id // This annotation specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // This annotation provides the specification of generation strategies for the values of primary keys.
    private Long id;

    private String name;        // Name of the product
    private String type;        // Type of the product
    private double price;       // Price of the product
    private int quantity;       // Quantity of the product

}
