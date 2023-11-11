package com.example.uzum_market.dto;

import com.example.uzum_market.model.*;
import lombok.Data;

import java.util.List;

@Data
public class ProductOneDTO {
    private Integer id;
    private Double rating;
    private Integer ratingCount;
    private Price price;
    private Double curPrice;
    private String description;
    private List<Color>  colors;
    private List<Specification>  specifications;
    private List<Attachment> attachments;
    private List<Comment> comments;
}
