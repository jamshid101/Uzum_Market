package com.example.uzum_market.repository;

import com.example.uzum_market.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {

    List<Category> findByParentCategoryId(Integer parentId);
}
