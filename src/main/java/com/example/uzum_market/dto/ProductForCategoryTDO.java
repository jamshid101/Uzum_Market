package com.example.uzum_market.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductForCategoryTDO {
    private String categoryName;
    private String productCount;
    private List<ProductDTO> productDTOS;
}
