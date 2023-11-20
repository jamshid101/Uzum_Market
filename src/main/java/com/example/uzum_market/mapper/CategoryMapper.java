package com.example.uzum_market.mapper;

import com.example.uzum_market.dto.CategoryDTO;
import com.example.uzum_market.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target ="categoryList", source = "null")
    CategoryDTO mapToCategoryDTO(Category category);
}
