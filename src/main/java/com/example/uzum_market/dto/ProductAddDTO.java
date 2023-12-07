package com.example.uzum_market.dto;

import jakarta.validation.constraints.*;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public record ProductAddDTO(
        @NotBlank String name,
        @NotBlank String description,
        @NotBlank String brand,
        @NotNull Integer categoryId,
        @NotNull Double discount,
        @NotNull  Boolean isStock,
        @NotNull Boolean isActive,
        @NotBlank String specificationType,
        @NotEmpty List<PriceAddDTO> priceAddDTOS,
        List<Integer> photoList


) {
}
