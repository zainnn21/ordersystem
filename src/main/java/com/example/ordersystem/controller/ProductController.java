package com.example.ordersystem.controller;

import com.example.ordersystem.model.Product;
import com.example.ordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // This annotation is used to create REST full web services using Spring MVC.
@RequestMapping("/api/products") // This annotation maps HTTP requests to handler methods of MVC and REST controllers.
public class ProductController {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private ProductService productService;

    // Endpoint to get all products with pagination
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size){
        return productService.getAllProducts(page,size);
    }

    // Endpoint to get a product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint to create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Endpoint to update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Optional<Product> productOpt = productService.getProductById(id);
        if(productOpt.isPresent()) {
            Product product = productOpt.get();
            product.setName(productDetails.getName());
            product.setType(productDetails.getType());
            product.setPrice(productDetails.getPrice());
            product.setQuantity(productDetails.getQuantity());
            Product updatedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
