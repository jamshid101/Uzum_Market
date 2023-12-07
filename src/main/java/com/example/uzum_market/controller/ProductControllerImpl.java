package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.service.AdvertisingService;
import com.example.uzum_market.service.PriceService;
import com.example.uzum_market.service.ProductService;
import com.example.uzum_market.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;
    private final PriceService priceService;
    private final AdvertisingService advertisingService;

    @Override
    public HttpEntity<ApiResult<PaginationDTO<ProductForCategoryDTO>>> productsList(MainCriteriaDTO mainCriteriaDTO, Integer categoryId) {
        ApiResult<PaginationDTO<ProductForCategoryDTO>> products = productService.getProductsList(mainCriteriaDTO, categoryId);
        return ResponseEntity.status(200).body(products);
    }

    @Override
    public HttpEntity<ApiResult<PaginationDTO<ProductDTO>>> productsOfSeller(MainCriteriaDTO mainCriteriaDTO) {
        Integer sellerId = CommonUtils.getCurrentUserFromContext().getId();
        ApiResult<PaginationDTO<ProductDTO>> products = productService.getProductsListForAdmin(mainCriteriaDTO, sellerId);
        return ResponseEntity.ok(products);
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> product(Integer productId) {
        ApiResult<ProductOneDTO> product = productService.getOne(productId);
        return ResponseEntity.ok(product);
    }

    @Override
    public HttpEntity<ApiResult<PriceDTO>> getPrice(Integer colorId, Integer specId) {
        ApiResult<PriceDTO> price = priceService.getPrice(colorId,specId);
        return ResponseEntity.ok(price);
    }

    @Override
    public HttpEntity<ApiResult<List<SlideDTO>>> getSlide() {
        ApiResult<List<SlideDTO>> listApiResult = advertisingService.getSlide();
        return ResponseEntity.ok(listApiResult);
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> add(ProductAddDTO productAddDTO) {
        Integer productId = productService.add(productAddDTO);
        ApiResult<ProductOneDTO> product = productService.getOne(productId);
        return ResponseEntity.ok(product);
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> edit(Integer id, ProductAddDTO productAddDTO) {
        Integer productId = productService.edit(productAddDTO, id);
        ApiResult<ProductOneDTO> product = productService.getOne(productId);
        return ResponseEntity.ok(product);
    }

    @Override
    public HttpEntity<ApiResult<ProductOneDTO>> changeStatus(Integer id) {
        productService.changeStatus(id);
        ApiResult<ProductOneDTO> product = productService.getOne(id);
        return ResponseEntity.ok(product);
    }

    @Override
    public HttpEntity<ApiResult<Integer>> remove(Integer id) {
        ApiResult<Integer> result = productService.delete(id);
        return ResponseEntity.status(202).body(result);
    }

    @Override
    public HttpEntity<ApiResult<PaginationDTO<ProductOneDTO>>> forAdmin(MainCriteriaDTO mainCriteriaDTO) {
        return null;
    }
}
