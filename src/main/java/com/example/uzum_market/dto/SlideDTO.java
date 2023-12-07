package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import lombok.Data;

@Data
public class SlideDTO {
    private Integer productId;

    private Integer id;

    private Attachment attachment;
}
