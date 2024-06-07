package com.example.ordersystem.controller;

import com.example.ordersystem.dto.OrderCartDTO;
import com.example.ordersystem.dto.OrderItemDTO;
import com.example.ordersystem.model.OrderCart;
import com.example.ordersystem.model.OrderItem;
import com.example.ordersystem.model.Product;
import com.example.ordersystem.service.OrderCartService;
import com.example.ordersystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // This annotation is used to create REST full web services using Spring MVC.
@RequestMapping("/api/order-cart") // This annotation maps HTTP requests to handler methods of MVC and REST controllers.
public class OrderCartController {
    @Autowired // This annotation allows Spring to resolve and inject collaborating beans into your bean.
    private OrderCartService orderCartService;

    @Autowired
    private ProductService productService;

    //Endpoint to retrieve an order cart by ID
    @GetMapping("/{id}")
    public ResponseEntity<OrderCartDTO> getOrderCartById(@PathVariable Long id) {
        Optional<OrderCart> orderCartopt = orderCartService.getOrderCartById(id);
        return orderCartopt.map(orderCart -> new ResponseEntity<>(new OrderCartDTO(orderCart), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //Endpoint to create new order cart
    @PostMapping
    public ResponseEntity<OrderCartDTO> createOrderCart(@RequestBody OrderCart orderCart) {
        OrderCart savedOrderCart = orderCartService.saveOrderCart(orderCart);
        return ResponseEntity.ok(new OrderCartDTO(savedOrderCart));
    }

    @PostMapping("/{orderCartId}/add-product")
    public ResponseEntity<OrderCartDTO> addProductToCart(
            @PathVariable Long orderCartId,
            @RequestBody OrderItemDTO orderItemDTO){
        Optional<OrderCart> orderCartOpt = orderCartService.getOrderCartById(orderCartId);
        if (orderCartOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Product> optionalProduct = productService.getProductById(orderItemDTO.getId());
        if(optionalProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        OrderCart orderCart = orderCartOpt.get();
        Product product = optionalProduct.get();
        OrderItem orderItem = new OrderItem(product, orderItemDTO.getQuantity());
        orderCart.addItem(orderItem);
        orderCartService.saveOrderCart(orderCart);
        return ResponseEntity.ok(new OrderCartDTO(orderCart));
    }

    // Endpoint to update an existing order cart by ID
    @PutMapping("/{id}")
    public ResponseEntity<OrderCart> updateOrderCart(@PathVariable Long id, @RequestBody OrderCart orderCart) {
        Optional<OrderCart> existingOrderCart = orderCartService.getOrderCartById(id);
        if (existingOrderCart.isPresent()) {
            orderCart.setId(id);
            OrderCart updatedOrderCart = orderCartService.saveOrderCart(orderCart);
            return new ResponseEntity<>(updatedOrderCart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint to delete an order cart by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderCart(@PathVariable Long id) {
        orderCartService.deleteOrderCart(id);
        return ResponseEntity.noContent().build();
    }

}
