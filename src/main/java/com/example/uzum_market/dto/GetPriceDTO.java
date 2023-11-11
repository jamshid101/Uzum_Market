package com.example.uzum_market.dto;

import lombok.Data;

@Data
public class GetPriceDTO {
    Double price;
    Double curPrice;
    Integer count;
    Integer sellCount;
}
