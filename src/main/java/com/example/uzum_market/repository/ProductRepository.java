package com.example.uzum_market.repository;

import com.example.uzum_market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
