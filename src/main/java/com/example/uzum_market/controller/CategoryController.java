package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.utils.AppConstants;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(CategoryController.BASE_PATH)
public interface CategoryController {
    String ALL_CATEGORY = "/api/all-category";
    String BASE_PATH = "/api";
    String REFRESH_TOKEN_PATH = "/child-category/{categoryId}";


    @PostMapping(ALL_CATEGORY)
    HttpEntity<ApiResult<List<CategoryDTO>>> categoryList();


}