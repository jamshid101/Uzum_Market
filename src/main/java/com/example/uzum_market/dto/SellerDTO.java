package com.example.uzum_market.dto;

import com.example.uzum_market.model.Attachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {
    private Integer id;

    private String name;

    private Attachment attachment;
}
