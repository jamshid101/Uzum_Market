package com.example.uzum_market.repository;

import com.example.uzum_market.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String username);

    Optional<User> findById(Integer id);
}
