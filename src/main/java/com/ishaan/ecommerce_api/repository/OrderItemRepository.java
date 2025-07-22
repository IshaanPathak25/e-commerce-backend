package com.ishaan.ecommerce_api.repository;

import com.ishaan.ecommerce_api.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends MongoRepository<OrderItem, String> {}
