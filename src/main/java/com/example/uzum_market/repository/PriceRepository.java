package com.example.uzum_market.repository;

import com.example.uzum_market.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price,Integer> {
     List<Price> getAllByProductIdOrderByPrice(Integer productId);
     Optional<Price> findByColor_IdAndSpecification_Id(Integer color_id, Integer specification_id);
     Optional<Price> findFirstByProductIdOrderByPrice(Integer productId);
}
