package com.example.ordersystem.service;

import com.example.ordersystem.model.Product;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // This annotation indicates that the class is a service and holds business logic.
public class ProductService {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private ProductRepository productRepository;

    // Retrieves a paginated list of products
    public Page<Product> getAllProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable);
    }

    // Retrieve a product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Save a new product or update an existing product
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete a product by ID
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
