package com.ishaan.ecommerce_api.repository;

import com.ishaan.ecommerce_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends MongoRepository<Product, String> {}