package com.ishaan.ecommerce_api.repository;

import com.ishaan.ecommerce_api.entity.Order;
import com.ishaan.ecommerce_api.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByUserUsername(String username);
    List<Order> findByUser(User user);
    
    List<Order> findByUserId(String userId);
}
