package com.example.uzum_market.repository;

import com.example.uzum_market.dto.RatingDTO;
import com.example.uzum_market.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface RatingRepository extends JpaRepository<Rating, Integer> {
        @Query(value = "SELECT new com.example.uzum_market.dto.RatingDTO(AVG(r.rating), COUNT(r.id))FROM Rating r WHERE r.product.id = :productId")
        RatingDTO findAverageAndTotalCountByProductId(@Param("productId") Integer productId);
}

