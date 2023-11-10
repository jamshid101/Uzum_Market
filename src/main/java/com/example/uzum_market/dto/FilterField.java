package com.example.uzum_market.dto;

import lombok.Data;
import uz.pdp.appbackend.enums.ColumnTypeEnum;

@Data
public class FilterField {

    private String column;

    private ColumnTypeEnum columnType;

    private ComparatorTypeEnum comparatorType;

    private FilterFieldValue value;

    /*
value
:
{searchingValue: "bah"}
searchingValue
:
"bah"
     */
}
