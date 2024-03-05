package com.example.uzum_market.repository;

import com.example.uzum_market.model.Payment;
import com.example.uzum_market.model.Promocode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PromoCodeRepository extends JpaRepository<Promocode, Integer> {
   Optional<Promocode> findByNameAndIsActive(String name, Boolean isActive);
}
