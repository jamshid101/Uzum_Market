package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import com.example.uzum_market.model.Color;
import com.example.uzum_market.model.Comment;
import lombok.Data;

import java.util.List;

@Data
public class ProductOneDTO {
    private Integer id;
    private Double rating;
    private Integer ratingCount;
    private Double price;
    private Double curPrice;
    private String description;
    private List<Color>  name;
    private List<Attachment> attachments;
    private List<Comment> comments;
}
