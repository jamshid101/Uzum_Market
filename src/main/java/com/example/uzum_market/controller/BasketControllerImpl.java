package com.example.uzum_market.controller;

import com.example.uzum_market.dto.AddBasketDTO;
import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BasketControllerImpl implements BasketController {

    private final BasketService basketService;

    @Override
    public HttpEntity<ApiResult<?>> saveBasket(AddBasketDTO addBAsketDTO) {
        return ResponseEntity.ok(basketService.saveBasket(addBAsketDTO));
    }
}
