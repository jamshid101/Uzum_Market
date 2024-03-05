package com.example.uzum_market.repository;

import com.example.uzum_market.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
    @Query(value = """
    SELECT oi FROM OrderItem oi 
    INNER JOIN oi.price p 
    INNER JOIN p.product pro 
    WHERE pro.seller.id = :sellerId 
""")
    List<OrderItem> findAllForSeller(@Param("sellerId") Integer sellerId);

}
