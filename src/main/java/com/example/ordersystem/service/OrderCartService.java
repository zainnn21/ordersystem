package com.example.ordersystem.service;

import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.repository.OrderCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderCartService {
    @Autowired
    private OrderCartRepository orderCartRepository;

    //Retrieve an order cart by ID
    public Optional<OrderCart> getOrderCartById(Long id) {
        return orderCartRepository.findById(id);
    }

    //Save a new order cart or update an existing order cart
    public OrderCart saveOrderCart(OrderCart orderCart) {
        return orderCartRepository.save(orderCart);
    }

    //Delete an order cart by ID
    public void deleteOrderCart(Long id) {
        orderCartRepository.deleteById(id);
    }
}
