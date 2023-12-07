package com.example.uzum_market.repository;

import com.example.uzum_market.model.Advertising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisingRepository extends JpaRepository<Advertising,Integer> {
    List<Advertising> findAllByIsActive(Boolean isActive);
}
