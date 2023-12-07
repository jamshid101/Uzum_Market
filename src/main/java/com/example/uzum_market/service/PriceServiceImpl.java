package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.PriceDTO;
import com.example.uzum_market.model.Price;
import com.example.uzum_market.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    @Override
    public ApiResult<PriceDTO> getPrice(Integer colorId, Integer specId) {
        Price price = priceRepository.findByColor_IdAndSpecification_Id(colorId, specId).orElseThrow(() -> new RuntimeException("Product not found"));
        Double discount = price.getProduct().getDiscount();
        Double price1 = price.getPrice();
                double discountPrice = price1 - (price1 * discount) / 100;
        PriceDTO priceDTO = PriceDTO.builder()
                .curPrice(discountPrice)
                .price(price1)
                .count(price.getCount())
                .id(price.getId())
                .build();
        return ApiResult.successResponse(priceDTO);

    }
}
