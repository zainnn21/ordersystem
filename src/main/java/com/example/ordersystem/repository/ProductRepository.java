package com.example.ordersystem.repository;

import com.example.ordersystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    // JpaRepository provides JPA related methods such as save, delete, and find, along with paging and sorting.
}
