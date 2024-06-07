package com.example.ordersystem.service;

import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.model.OrderItem;
import com.example.ordersystem.model.Product;
import com.example.ordersystem.repository.OrderCartRepository;
import com.example.ordersystem.repository.OrderItemRepository;
import com.example.ordersystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // This annotation indicates that the class is a service and holds business logic.
public class OrderCartService {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private OrderCartRepository orderCartRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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

    public OrderItem addProductToCart(Long cartId, Long productId, int quantity) {
        Optional<OrderCart> orderCartOpt = orderCartRepository.findById(cartId);
        Optional<Product> productOpt = productRepository.findById(productId); // Retrieve product by ID>

        if (orderCartOpt.isPresent() && productOpt.isPresent()) {
            OrderCart orderCart = orderCartOpt.get();
            Product product = productOpt.get();

            // check if the product is already exists in the cart
            Optional<OrderItem> existingItemOpt = orderCart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst();

            OrderItem orderItem;
            if (existingItemOpt.isPresent()) {
                orderItem = existingItemOpt.get();
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
            } else {
                orderItem = new OrderItem();
                orderItem.setOrderCart(orderCart);
                orderItem.setProduct(product);
                orderItem.setQuantity(quantity);
                orderCart.getItems().add(orderItem);
            }
            orderItemRepository.save(orderItem);
            orderCartRepository.save(orderCart);
            return orderItem;
        }
        return null;
    }
}

