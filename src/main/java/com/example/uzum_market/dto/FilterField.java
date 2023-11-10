package com.example.uzum_market.dto;

import com.example.uzum_market.enums.ColumnTypeEnum;
import com.example.uzum_market.enums.ComparatorTypeEnum;
import lombok.Data;

@Data
public class FilterField {

    private String column;

    private ColumnTypeEnum columnType;

    private ComparatorTypeEnum comparatorType;

    private FilterFieldValue value;


}
