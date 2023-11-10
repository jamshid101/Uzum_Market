package com.example.uzum_market.dto;

import com.example.uzum_market.utils.AppConstants;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {

    private String accessToken;

    private String refreshToken;

    @Builder.Default
    private final String tokenType = AppConstants.BEARER_TYPE;
}