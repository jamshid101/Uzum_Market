package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {
    private String name;
    private Integer id;
    private Attachment attachment;
    private List<CategoryDTO> categoryList;
}
