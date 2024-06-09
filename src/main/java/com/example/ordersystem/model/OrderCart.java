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

    private String customer;
    private String address;

    @OneToMany(mappedBy = "orderCart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public OrderCart(String customer, String address) {
        this.customer = customer;
        this.address = address;
    }

    @Transient // not a persistent field, derived
    public double getTotalPrice() {
        return items.stream().mapToDouble(OrderItem::getTotalPrice).sum();
    };

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrderCart(this);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrderCart(null);
    }


//    @Transient // This annotation specifies that the field is not persistent.
//    private Double total; // Total price of the order cart


}
