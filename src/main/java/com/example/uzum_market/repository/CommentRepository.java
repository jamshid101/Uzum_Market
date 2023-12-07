package com.example.uzum_market.repository;

import com.example.uzum_market.model.Comment;
import com.example.uzum_market.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
     List<Comment> getAllByProductId(Integer productId);
}
