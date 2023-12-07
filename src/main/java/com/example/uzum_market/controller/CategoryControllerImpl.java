package com.example.uzum_market.controller;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.CategoryDTO;
import com.example.uzum_market.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public HttpEntity<ApiResult<List<CategoryDTO>>> categoryList() {
       return ResponseEntity.ok(categoryService.all());
    }

    @Override
    public HttpEntity<ApiResult<List<CategoryDTO>>> childCategoryList(Integer categoryId) {
        return ResponseEntity.ok(categoryService.childByCategoryId(categoryId));
    }
}
