package com.example.uzum_market.controller;

import com.example.uzum_market.dto.AddBasketDTO;
import com.example.uzum_market.dto.ApiResult;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = BasketController.BASE_PATH)
public interface BasketController {

    String BASE_PATH = "/api/basket";
    String UPLOAD_PATH = "upload";

    @PostMapping(UPLOAD_PATH)
    HttpEntity<ApiResult<?>> saveBasket(AddBasketDTO addBAsketDTO);


}
