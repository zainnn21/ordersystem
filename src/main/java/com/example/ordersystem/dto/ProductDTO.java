package com.example.ordersystem.dto;

import com.example.ordersystem.model.Product;
import lombok.Data;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
public class ProductDTO {
    private Long id; // Product ID
    private String name;
    private String type;
    private int quantity;
    private double price; // Total price for this item

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.type = product.getType();
        this.quantity = product.getQuantity();
        this.price = product.getPrice();
    }
}
