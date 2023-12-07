package com.example.uzum_market.repository;

import com.example.uzum_market.model.HistoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryItemRepository extends JpaRepository<HistoryItem, Integer> {
boolean existsHistoryItemByProductId(Integer productId);

}
