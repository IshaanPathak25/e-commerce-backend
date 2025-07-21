package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    // Simulated in-memory cart for demo/testing
    private final Map<String, Map<Long, Integer>> userCarts = new HashMap<>();

    private final ProductRepository productRepository;

    public void addToCart(String username, Long productId, int quantity) {
        userCarts.putIfAbsent(username, new HashMap<>());
        Map<Long, Integer> cart = userCarts.get(username);
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
    }

    public Map<Long, Integer> getCart(String username) {
        return userCarts.getOrDefault(username, new HashMap<>());
    }

    public void clearCart(String username) {
        userCarts.remove(username);
    }
}
