package com.example.ordersystem.service;

import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.model.OrderItem;
import com.example.ordersystem.model.Product;
import com.example.ordersystem.repository.OrderCartRepository;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // This annotation indicates that the class is a service and holds business logic.
public class OrderCartService {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private OrderCartRepository orderCartRepository;


    @Autowired
    private ProductRepository productRepository;

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

    public OrderCart addProductToCart(Long orderCartId, Long productId, int quantity) {
        Optional<OrderCart> orderCartOpt = orderCartRepository.findById(orderCartId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (orderCartOpt.isPresent() && productOpt.isPresent()) {
            OrderCart orderCart = orderCartOpt.get();
            Product product = productOpt.get();
            OrderItem orderItem = new OrderItem(product, quantity);
            orderCart.addItem(orderItem);
            return orderCartRepository.save(orderCart);
        } else {
            throw new RuntimeException("Order cart or product not found");
        }
    }
}

