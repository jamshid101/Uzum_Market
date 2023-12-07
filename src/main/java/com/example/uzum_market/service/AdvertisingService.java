package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.SlideDTO;

import java.util.List;

public interface AdvertisingService {

    ApiResult<List<SlideDTO>> getSlide();

}
