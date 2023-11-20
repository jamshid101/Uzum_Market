package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    private Integer id;
    private String nameUz;
    private String nameRu;
    private Attachment attachment;
    private List<CategoryDTO> categoryList;
}
