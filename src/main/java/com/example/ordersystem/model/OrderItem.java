package com.example.ordersystem.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
@Embeddable // This annotation specifies that the class can be embedded in another entity.
public class OrderItem {
    private String productName; // Name of the product
    private String productType; // Type of the product
    private Double productPrice; // Price of the product
    private Integer quantity; // Quantity of the product

    public Double getTotal() {
        return productPrice * quantity;
    } // Calculates the total price for the quantity of products.
}
