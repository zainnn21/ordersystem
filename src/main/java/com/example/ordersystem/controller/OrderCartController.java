package com.example.ordersystem.controller;

import com.example.ordersystem.dto.OrderCartDTO;
import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.service.OrderCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // This annotation is used to create RESTful web services using Spring MVC.
@RequestMapping("/api/order-cart") // This annotation maps HTTP requests to handler methods of MVC and REST controllers.
public class OrderCartController {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private OrderCartService orderCartService;

    //Endpoint to retrieve an order cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderCartDTO> getOrderCartById(@PathVariable Long id) {
        Optional<OrderCart> orderCart = orderCartService.getOrderCartById(id);
        return orderCart.map(value -> new ResponseEntity<>(orderCartService.toDTO(value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint to create new order cart
    @PostMapping
    public ResponseEntity<OrderCartDTO> createOrderCart(@RequestBody OrderCartDTO orderCartDTO) {
        OrderCart orderCart = orderCartService.toEntity(orderCartDTO);
        return new ResponseEntity<>(orderCartService.toDTO(orderCartService.saveOrderCart(orderCart)), HttpStatus.CREATED);
    }

    // Endpoint to update an existing order cart by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderCart> updateOrderCart(@PathVariable Long id, @RequestBody OrderCartDTO orderCartDTO) {
        Optional<OrderCart> existingOrderCart = orderCartService.getOrderCartById(id);
        if (existingOrderCart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        OrderCart orderCart = orderCartService.toEntity(orderCartDTO);
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
