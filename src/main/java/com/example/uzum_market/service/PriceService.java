package com.example.uzum_market.service;


import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.PriceDTO;

public interface PriceService {

    ApiResult<PriceDTO> getPrice(Integer colorId, Integer specId);
}
