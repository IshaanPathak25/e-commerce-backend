package com.ishaan.ecommerce_api.controller;

import com.ishaan.ecommerce_api.service.CartService;
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
            @PathVariable String productId,  // CHANGED Long → String
            @RequestParam int quantity) {
        cartService.addToCart(user.getUsername(), productId, quantity);
        return ResponseEntity.ok("Added to cart");
    }

    @GetMapping
    public ResponseEntity<Map<String, Integer>> viewCart(@AuthenticationPrincipal UserDetails user) { // CHANGED Long → String
        return ResponseEntity.ok(cartService.getCart(user.getUsername()));
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(@AuthenticationPrincipal UserDetails user) {
        cartService.clearCart(user.getUsername());
        return ResponseEntity.ok("Cart cleared");
    }
}
