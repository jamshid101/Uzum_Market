package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController(ProductController.BASE_PATH)
public interface ProductController {
    String BASE_PATH = "/api/product";
    String ALL_CATE_PRODUCT_PATH = "/category-products/{categoryId}";
    String PRODUCT_PATH = "";
    String CHANGE_STATUS_PATH = "/change-status/{id}";
    String LIST_FOR_SELLER_PATH = "/for-seller";



    @GetMapping(ALL_CATE_PRODUCT_PATH)
    HttpEntity<ApiResult<List<ProductForCategoryTDO>>> productsList(@RequestBody MainCriteriaDTO mainCriteriaDTO, @PathVariable Integer categoryId );

    @GetMapping("/{productId}")
    HttpEntity<ApiResult<List<ProductOneDTO>>> product(@PathVariable Integer productId);


    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PostMapping()
    HttpEntity<ApiResult<ProductOneDTO>> add(@Valid @RequestBody ProductAddDTO productAddDTO);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PutMapping(PRODUCT_PATH)
    HttpEntity<ApiResult<ProductOneDTO>> edit(@PathVariable Integer id, @Valid @RequestBody ProductAddDTO productAddDTO);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PatchMapping(CHANGE_STATUS_PATH)
    HttpEntity<ApiResult<ProductOneDTO>> changeStatus(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    HttpEntity<ApiResult<String>> remove(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PostMapping(LIST_FOR_SELLER_PATH)
    HttpEntity<ApiResult<PaginationDTO<ProductOneDTO>>> forAdmin(@RequestBody MainCriteriaDTO mainCriteriaDTO);



   
}