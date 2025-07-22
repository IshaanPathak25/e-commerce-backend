package com.ishaan.ecommerce_api.controller;

import com.ishaan.ecommerce_api.dto.AuthRequest;
import com.ishaan.ecommerce_api.dto.AuthResponse;
import com.ishaan.ecommerce_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        String response = userService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse token = userService.login(request);
        return ResponseEntity.ok(token);
    }
}
