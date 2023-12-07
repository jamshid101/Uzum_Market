package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.SlideDTO;
import com.example.uzum_market.mapper.AdvertisingMapper;
import com.example.uzum_market.model.Advertising;
import com.example.uzum_market.repository.AdvertisingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AdvertisingServiceImpl implements AdvertisingService {

    private final AdvertisingMapper advertisingMapper;
    private final AdvertisingRepository advertisingRepository;
    @Override
    public ApiResult<List<SlideDTO>> getSlide() {
        List<Advertising> allByIsActive = advertisingRepository.findAllByIsActive(true);
        List<SlideDTO> slideDTO = advertisingMapper.toSlideDTO(allByIsActive);
        return ApiResult.successResponse(slideDTO);
    }
}
