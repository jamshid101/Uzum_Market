package com.example.uzum_market.repository;

import com.example.uzum_market.model.Balance;
import com.example.uzum_market.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance,Integer> {

}
