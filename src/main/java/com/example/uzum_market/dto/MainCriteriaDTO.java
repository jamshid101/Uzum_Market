package com.example.uzum_market.dto;

import lombok.Data;

import java.util.List;

@Data
public class MainCriteriaDTO {

    private int page = 0;

    private int size = 10;

    private List<SortDTO> sorts;//[] null

    private FilterDTO filter;
}
