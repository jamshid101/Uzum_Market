package com.example.uzum_market.dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public record ProductAddDTO(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String specificationType,
        @NotEmpty List<PriceAddDTO> priceAddDTOS,
        List<MultipartHttpServletRequest> photoList


) {
}
