package com.example.uzum_market.dto;

import lombok.Data;
import org.springframework.data.domain.Sort;

@Data
public class SortDTO {

    private Sort.Direction direction;

    private String column;
}