package com.example.ecommerce.controller;

import com.example.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addToCart(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long productId,
            @RequestParam int quantity) {
        cartService.addToCart(user.getUsername(), productId, quantity);
        return ResponseEntity.ok("Added to cart");
    }

    @GetMapping
    public ResponseEntity<Map<Long, Integer>> viewCart(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(cartService.getCart(user.getUsername()));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@AuthenticationPrincipal UserDetails user) {
        cartService.clearCart(user.getUsername());
        return ResponseEntity.ok("Cart cleared");
    }
}
