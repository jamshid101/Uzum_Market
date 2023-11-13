package com.example.uzum_market.service;

import com.example.uzum_market.dto.AddBasketDTO;
import com.example.uzum_market.dto.ApiResult;

public interface BasketService {

    ApiResult<?> saveBasket(AddBasketDTO addBasketDTO);
}
