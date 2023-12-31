package com.example.uzum_market.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public record ColorAddDTO (
        List<Integer> photoList,
        @NotBlank String name

){
}
