package com.ishaan.ecommerce_api.repository;

import com.ishaan.ecommerce_api.entity.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {
    List<OrderItem> findByUserId(String userId); // ✅ Add this method
}
