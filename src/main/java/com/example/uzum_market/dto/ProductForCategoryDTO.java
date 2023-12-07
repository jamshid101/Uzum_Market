package com.example.uzum_market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductForCategoryDTO {
    private String categoryName;
    private int productCount;
    private List<ProductDTO> productDTOS;
}
