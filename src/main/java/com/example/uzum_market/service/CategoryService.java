package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.CategoryDTO;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.mapper.CategoryMapper;
import com.example.uzum_market.model.Category;
import com.example.uzum_market.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public ApiResult<List<CategoryDTO>> all() {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categoryRepository.findAll()) {
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .nameUz(category.getNameUz())
                    .nameRu(category.getNameRu())
                    .attachment(category.getAttachment())
                    .categoryList(childByCategoryId(category.getId()).getData())
                    .build();
            categoryDTOS.add(categoryDTO);
        }

        return ApiResult.successResponse(categoryDTOS);
    }

    public ApiResult<List<CategoryDTO>> childByCategoryId(Integer categoryId) {
        List<Category> allById = categoryRepository.findByParentCategoryId(categoryId);
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : allById) {
            categoryDTOS.add(categoryMapper.mapToCategoryDTO(category));
        }
        System.out.println(categoryDTOS);
        return ApiResult.successResponse(categoryDTOS);
    }
}
