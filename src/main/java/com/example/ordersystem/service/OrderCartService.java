package com.example.ordersystem.service;

import com.example.ordersystem.dto.OrderCartDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.model.OrderItem;
import com.example.ordersystem.repository.OrderCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service // This annotation indicates that the class is a service and holds business logic.
public class OrderCartService {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private OrderCartRepository orderCartRepository;

    // Converts an OrderCart entity to an OrderCartDTO
    public OrderCartDTO toDTO(OrderCart orderCart) {
        OrderCartDTO orderCartDTO = new OrderCartDTO();
        orderCartDTO.setId(orderCart.getId());
        orderCartDTO.setCustomer(orderCart.getCustomer());
        orderCartDTO.setAddress(orderCart.getAddress());
        orderCartDTO.setItems(orderCart.getItems().stream().map(this::toDTO).collect(Collectors.toList()));
        orderCartDTO.setTotal(orderCart.getTotal());
        return orderCartDTO;
    }

    // Converts an OrderCartDTO to an OrderCart entity
    public OrderCart toEntity(OrderCartDTO orderCartDTO) {
        OrderCart orderCart = new OrderCart();
        orderCart.setCustomer(orderCartDTO.getCustomer());
        orderCart.setAddress(orderCartDTO.getAddress());
        orderCart.setItems(orderCartDTO.getItems().stream().map(this::toEntity).collect(Collectors.toList()));
        orderCart.setTotal(orderCartDTO.getTotal());
        return orderCart;
    }

    // Converts an OrderItem entity to an OrderItemDTO
    private OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProductName(orderItem.getProductName());
        orderItemDTO.setProductType(orderItem.getProductType());
        orderItemDTO.setProductPrice(orderItem.getProductPrice());
        orderItemDTO.setQuantity(orderItem.getQuantity());
        orderItemDTO.setTotal(orderItem.getTotal());
        return orderItemDTO;
    }

    // Converts an OrderItemDTO to an OrderItem entity
    private OrderItem toEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductName(orderItemDTO.getProductName());
        orderItem.setProductType(orderItemDTO.getProductType());
        orderItem.setProductPrice(orderItemDTO.getProductPrice());
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItem;
    }

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
