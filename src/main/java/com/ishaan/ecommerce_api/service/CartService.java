package com.ishaan.ecommerce_api.service;

import com.ishaan.ecommerce_api.entity.Product;
import com.ishaan.ecommerce_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {

    // Simulated in-memory cart for demo/testing
    private final Map<String, Map<String, Integer>> userCarts = new HashMap<>();

    private final ProductRepository productRepository;

    public void addToCart(String username, String productId, int quantity) {
        userCarts.putIfAbsent(username, new HashMap<>());
        Map<String, Integer> cart = userCarts.get(username);
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
    }

    public Map<String, Integer> getCart(String username) {
        return userCarts.getOrDefault(username, new HashMap<>());
    }

    public void clearCart(String username) {
        userCarts.remove(username);
    }
}
