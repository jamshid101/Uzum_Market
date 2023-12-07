package com.example.uzum_market.repository;

import com.example.uzum_market.model.Product;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM get_ids_by_query(:query)")
    List<Integer> findAllByMyQuery(String query);

    Integer countAllByCategoryId(Integer categoryId);

    Integer countAllBySellerId(Integer sellerId);

    Optional<Product> findById(Integer id);

    boolean existsById( Integer id);

}
