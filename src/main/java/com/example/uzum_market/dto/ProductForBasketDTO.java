package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import com.example.uzum_market.model.Price;
import lombok.Data;

import java.util.List;
@Data
public class ProductForBasketDTO {
    private Integer productId;
    private Integer count;
    private Price price;
    private String  name;
    private List<Attachment> attachments;
}
