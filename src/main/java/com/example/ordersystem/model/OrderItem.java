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

    @ManyToOne // Specifies a many-to-one relationship with another entity.
    @JoinColumn(name = "order_cart_id") // Specifies the foreign key column.
    private OrderCart orderCart; // Reference to the associated OrderCart entity.

    @ManyToOne // Specifies a many-to-one relationship with another entity.
    @JoinColumn(name = "product_id") // Specifies the foreign key column.
    private Product product; // Reference to the associated Product entity.

    private int quantity; // The quantity of the product ordered.

    private double totalPrice;  // The total price for the order item.

    // Constructor to initialize OrderItem with a product and quantity.
    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Transient // Not a persistent field, derived
    public double getTotalPrice() {
        // Calculates the total price based on product price and quantity.
        return product.getPrice() * quantity;
    }
}
