package com.example.uzum_market.service;

import com.example.uzum_market.dto.*;


public interface ProductService {
    ApiResult<PaginationDTO<ProductForCategoryDTO>> getProductsList(MainCriteriaDTO mainCriteriaDTO, Integer categoryId);

    ApiResult<PaginationDTO<ProductDTO>> getProductsListForAdmin(MainCriteriaDTO mainCriteriaDTO, Integer sellerId);

    ApiResult<ProductOneDTO> getOne(Integer productId);

    Integer add(ProductAddDTO productAddDTO);

    Integer edit(ProductAddDTO productAddDTO, Integer id);

    ApiResult<Integer> delete(Integer id);

    void changeStatus(Integer id);
}
