package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;

import java.util.List;

public class ProductDTO {
    private Integer id;
    private Double rating;
    private Integer ratingCount;
    private Double price;
    private Double curPrice;

    private String  name;
    private List<Attachment> attachments;


}
