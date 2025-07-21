package com.example.ecommerce.service;

import com.example.ecommerce.dto.OrderRequest;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(Long userId, OrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Cart> cartItems = cartRepository.findByUser(user);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }

        Order order = Order.builder()
                .user(user)
                .products(cartItems.stream().map(Cart::getProduct).toList())
                .totalAmount(cartItems.stream()
                        .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                        .sum())
                .createdAt(LocalDateTime.now())
                .address(request.getAddress())
                .build();

        // Reduce stock of each product
        for (Cart item : cartItems) {
            Product product = item.getProduct();
            int updatedStock = product.getStock() - item.getQuantity();
            if (updatedStock < 0) {
                throw new RuntimeException("Product " + product.getName() + " is out of stock.");
            }
            product.setStock(updatedStock);
            productRepository.save(product);
        }

        // Clear cart
        cartRepository.deleteAll(cartItems);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return orderRepository.findByUser(user);
    }
}
