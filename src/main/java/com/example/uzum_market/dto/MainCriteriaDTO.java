package com.example.uzum_market.dto;

import lombok.Data;

import java.util.List;


@Data
public class MainCriteriaDTO {

    private int page = 0;

    private int size = 20;

    private String color;

    private String specification;

    private String brand;

    private String search;

    private SortDTO sortColumn;

    private Double minPrice;

    private Double maxPrice;

    private DateDTO dateDTO;
}
