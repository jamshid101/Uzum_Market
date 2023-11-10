package com.example.uzum_market.dto;

import com.example.uzum_market.enums.FilterOperatorEnum;
import lombok.Data;

import java.util.List;

@Data
public class FilterDTO {

    private String search;//undatio

    private List<String> searchingColumns;//[author, title]

    private FilterOperatorEnum filterOperator; //OR

    private List<FilterField> filterFields;//


}
