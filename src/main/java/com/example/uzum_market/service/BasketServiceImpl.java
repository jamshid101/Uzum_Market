package com.example.uzum_market.service;

import com.example.uzum_market.dto.AddBasketDTO;
import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.*;
import com.example.uzum_market.repository.BasketRepository;
import com.example.uzum_market.repository.PriceRepository;
import com.example.uzum_market.repository.ProductRepository;
import com.example.uzum_market.utils.CommanUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final PriceRepository priceRepository;

    @Override
    public ApiResult<?> saveBasket(AddBasketDTO addBasketDTO) {

        Product product = productRepository.findById(addBasketDTO.getProductId())
                .orElseThrow(() -> RestException.restThrow("Iltimos To'g'ri ma'lumot kiritilganiga etibor bering", HttpStatus.BAD_REQUEST));
        Price price = priceRepository.findById(addBasketDTO.getPriceId())
                .orElseThrow(() -> RestException.restThrow("Iltimos To'g'ri ma'lumot kiritilganiga etibor bering", HttpStatus.BAD_REQUEST));

        User user = CommanUtils.getCurrentUserFromContext();
        Optional<Basket> basketUser = basketRepository.findById(user.getId());
        if (basketUser.isPresent()) {

        }

        return null;
    }
}
