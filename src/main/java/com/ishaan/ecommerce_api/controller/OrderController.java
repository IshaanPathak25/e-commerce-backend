package com.ishaan.ecommerce_api.controller;

import com.ishaan.ecommerce_api.entity.Order;
import com.ishaan.ecommerce_api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // Accepts: { "productId": quantity, ... }
    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(
        @AuthenticationPrincipal UserDetails user,
        @RequestBody OrderRequest orderRequest) {
    return ResponseEntity.ok(orderService.placeOrder(user.getUsername(), orderRequest));
}


    @GetMapping("/my")
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(orderService.getUserOrders(user.getUsername()));
    }
}
