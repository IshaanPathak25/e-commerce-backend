package com.ishaan.ecommerce_api.service;

import com.ishaan.ecommerce_api.dto.OrderRequest;
import com.ishaan.ecommerce_api.entity.Order;
import com.ishaan.ecommerce_api.entity.OrderItem;
import com.ishaan.ecommerce_api.entity.Product;
import com.ishaan.ecommerce_api.entity.User;
import com.ishaan.ecommerce_api.repository.OrderRepository;
import com.ishaan.ecommerce_api.repository.OrderItemRepository;
import com.ishaan.ecommerce_api.repository.ProductRepository;
import com.ishaan.ecommerce_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public Order placeOrder(String userId, OrderRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<OrderItem> cartItems = orderItemRepository.findByUserId(userId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot place order.");
        }

        double total = cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        Order order = Order.builder()
                .userId(userId)
                .products(cartItems.stream().map(OrderItem::getProduct).toList())
                .totalAmount(total)
                .createdAt(LocalDateTime.now())
                .address(request.getAddress())
                .build();

        for (OrderItem item : cartItems) {
            Product product = item.getProduct();
            int updatedStock = product.getStock() - item.getQuantity();
            if (updatedStock < 0) {
                throw new RuntimeException("Product " + product.getName() + " is out of stock.");
            }
            product.setStock(updatedStock);
            productRepository.save(product);
        }

        orderItemRepository.deleteAll(cartItems);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersForUser(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
