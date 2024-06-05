package com.example.ordersystem.controller;

import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order-cart")//Base URL for order cart API
public class OrderCartController {
    @Autowired
    private OrderCartService orderCartService;

    //Endpoint to retrieve an order cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderCart> getOrderCartById(@PathVariable Long id) {
        Optional<OrderCart> orderCart = orderCartService.getOrderCartById(id);
        return orderCart.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint to create new order cart
    @PostMapping
    public ResponseEntity<OrderCart> createOrderCart(@RequestBody OrderCart orderCart) {
        return new ResponseEntity<>(orderCartService.saveOrderCart(orderCart), HttpStatus.CREATED);
    }

    // Endpoint to update an existing order cart by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderCart> updateOrderCart(@PathVariable Long id, @RequestBody OrderCart orderCart) {
        Optional<OrderCart> existingOrderCart = orderCartService.getOrderCartById(id);
        if (existingOrderCart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderCart.setId(id);
        return new ResponseEntity<>(orderCartService.saveOrderCart(orderCart), HttpStatus.OK);
    }

    // Endpoint to delete an order cart by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderCart(@PathVariable Long id) {
        Optional<OrderCart> existingOrderCart = orderCartService.getOrderCartById(id);
        if (existingOrderCart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderCartService.deleteOrderCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
