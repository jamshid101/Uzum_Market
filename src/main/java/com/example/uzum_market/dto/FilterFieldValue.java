package com.example.uzum_market.dto;

import lombok.Data;

@Data
public class FilterFieldValue {

    private String searchingValue;

    private Double minValue;

    private Double maxValue;

    private Long startDate;

    private Long endDate;

    private boolean checkBoxValue;
}
