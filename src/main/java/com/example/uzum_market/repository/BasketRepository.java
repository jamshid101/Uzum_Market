package com.example.uzum_market.repository;

import com.example.uzum_market.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket,Integer> {

    Optional<Basket> findByUserId(Integer user_id);
}
