package com.example.uzum_market.repository;

import com.example.uzum_market.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price,Integer> {
}
