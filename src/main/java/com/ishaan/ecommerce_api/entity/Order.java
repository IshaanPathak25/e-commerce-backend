package com.ishaan.ecommerce_api.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    private String id;

    private String userId;

    private List<Product> products;

    private double totalAmount;

    private String address;

    private LocalDateTime createdAt;

    @DBRef
    private User user;

    @DBRef
    private List<OrderItem> items;

    private LocalDateTime orderDate;
}
