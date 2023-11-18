package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController{

    private final ProductService productService;
    @Override
    public HttpEntity<ApiResult<PaginationDTO<ProductForCategoryTDO>>> productsList(MainCriteriaDTO mainCriteriaDTO, Integer categoryId) {
      ApiResult<PaginationDTO<ProductForCategoryTDO>> products=  productService.getProductsList(mainCriteriaDTO, categoryId);
        return ResponseEntity.status(200).body(products);
    }

    @Override
    public HttpEntity<ApiResult<List<ProductForCategoryTDO>>> productsOfSeller(MainCriteriaDTO mainCriteriaDTO, String sellerId) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> product(Integer productId) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<List<GetPriceDTO>>> getPrice(Integer colorId, Integer specId) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<List<SlideDTO>>> getSlide(String productId) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> add(ProductAddDTO productAddDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> edit(Integer id, ProductAddDTO productAddDTO) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> changeStatus(Integer id) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<String>> remove(Integer id) {
        return null;
    }

    @Override
    public HttpEntity<ApiResult<PaginationDTO<ProductOneDTO>>> forAdmin(MainCriteriaDTO mainCriteriaDTO) {
        return null;
    }
}
