package com.example.uzum_market.repository;

import com.example.uzum_market.model.Address;
import com.example.uzum_market.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
