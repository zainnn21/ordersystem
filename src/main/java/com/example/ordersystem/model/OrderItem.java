package com.example.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, toString, equals, and hashcode methods.
@Entity
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_cart_id")
    private OrderCart orderCart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Transient // Not a persistent field, derived
    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }
}
