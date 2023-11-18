package com.example.uzum_market.service;

import com.example.uzum_market.dto.AddBasketDTO;

public interface BasketService {

    ApiResult<?> saveBasket(AddBasketDTO addBasketDTO);
}
