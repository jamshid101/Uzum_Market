package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.MainCriteriaDTO;
import com.example.uzum_market.dto.PaginationDTO;
import com.example.uzum_market.dto.ProductForCategoryTDO;
import com.example.uzum_market.utils.MakeQuery;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ApiResult<PaginationDTO<ProductForCategoryTDO>> getProductsList(MainCriteriaDTO mainCriteriaDTO, Integer categoryId) {
        String query = MakeQuery.makeQuery(mainCriteriaDTO,"product",categoryId);
        System.out.println(query);
        return null;
    }
}
