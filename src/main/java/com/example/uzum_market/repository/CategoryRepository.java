package com.example.uzum_market.repository;

import com.example.uzum_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    @Query("SELECT e FROM Category e WHERE e.parentCategory IS NULL")
    List<Category> findEntitiesWithNullParentCategoryIds();

    List<Category> findByParentCategoryId(Integer parentCategory_id);
}
