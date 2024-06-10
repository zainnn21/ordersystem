package com.example.ordersystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity // This annotation specifies that the class is an entity and is mapped to a database table.
@Data // Lombok generates getters, setters, equals, hash, and toString methods
@NoArgsConstructor
public class OrderCart {
    @Id // This annotation specifies the primary key of an entity.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto Increment
    private Long id;

    private String customer; // The name of the customer
    private String address;  // The address of the customer


    // One-to-many relationship with OrderItem, cascade all operations, and remove orphan items
    @OneToMany(mappedBy = "orderCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();


    @Transient // not a persistent field, derived
    public double getTotalPrice() {
        // Calculates the total price by summing up the total price of each order item.
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    };

    // Adds an item to the order cart and sets the orderCart reference in the item.
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrderCart(this);
    }


}
