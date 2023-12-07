package com.example.uzum_market.dto;

import com.example.uzum_market.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductOneDTO {
    private Integer id;

    private RatingDTO ratingDTO;

    private PriceDTO price;

    private SellerDTO sellerDTO;

    private Integer sellCount;

    private String description;

    private List<Color> colors;

    private List<Specification> specifications;

    private List<Attachment> attachments;

    private List<Comment> comments;
}
