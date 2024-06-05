package com.example.ordersystem.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class OrderItem {
    private String productName;
    private String productType;
    private Double productPrice;
    private Integer quantity;

    //Calculate total price for this order item
    public Double getTotal() {
        return productPrice * quantity;
    }
}
