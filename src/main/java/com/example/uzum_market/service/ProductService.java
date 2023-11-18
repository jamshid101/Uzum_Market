package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.MainCriteriaDTO;
import com.example.uzum_market.dto.PaginationDTO;
import com.example.uzum_market.dto.ProductForCategoryTDO;


public interface ProductService {
    ApiResult<PaginationDTO<ProductForCategoryTDO>> getProductsList(MainCriteriaDTO mainCriteriaDTO, Integer categoryId);
}
