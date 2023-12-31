package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Integer productId;

    private RatingDTO ratingDTO;

    private Double price;

    private Double curPrice;

    private String name;

    private List<Attachment> attachments;


}
