package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(ProductController.BASE_PATH)
public interface ProductController {
    String BASE_PATH = "/api/product";
    String ALL_CATE_PRODUCT_PATH = "/category-products/{categoryId}";
    String PRICE_PATH = "price/";
    String PRODUCT_PATH = "";
    String SLIDE_PATH = "/slide";
    String ADD_PRODUCT_PATH = "/add-product";
    String CHANGE_STATUS_PATH = "/change-status/{id}";
    String LIST_FOR_SELLER_PATH = "/for-seller";
    String ALL_SELLER_PRODUCT_PATH = "/seller-products";



    @PostMapping(ALL_CATE_PRODUCT_PATH)
    HttpEntity<ApiResult<PaginationDTO<ProductForCategoryDTO>>> productsList(@RequestBody(required = false) MainCriteriaDTO mainCriteriaDTO, @PathVariable Integer categoryId);

    @PostMapping(ALL_SELLER_PRODUCT_PATH)
    HttpEntity<ApiResult<PaginationDTO<ProductDTO>>> productsOfSeller(@RequestBody MainCriteriaDTO mainCriteriaDTO);

    @GetMapping("/{productId}")
    HttpEntity<ApiResult<ProductOneDTO>> product(@PathVariable Integer productId);

    @GetMapping(PRICE_PATH)
    HttpEntity<ApiResult<PriceDTO>> getPrice(@RequestParam Integer colorId, @RequestParam Integer specId);

    @GetMapping(SLIDE_PATH)
    HttpEntity<ApiResult<List<SlideDTO>>> getSlide();


//    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PostMapping(ADD_PRODUCT_PATH)
    HttpEntity<ApiResult<ProductOneDTO>> add(@Valid @RequestBody ProductAddDTO productAddDTO);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PutMapping(PRODUCT_PATH)
    HttpEntity<ApiResult<ProductOneDTO>> edit(@PathVariable Integer id, @Valid @RequestBody ProductAddDTO productAddDTO);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PatchMapping(CHANGE_STATUS_PATH)
    HttpEntity<ApiResult<ProductOneDTO>> changeStatus(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    HttpEntity<ApiResult<Integer>> remove(@PathVariable Integer id);

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @PostMapping(LIST_FOR_SELLER_PATH)
    HttpEntity<ApiResult<PaginationDTO<ProductOneDTO>>> forAdmin(@RequestBody MainCriteriaDTO mainCriteriaDTO);




}
