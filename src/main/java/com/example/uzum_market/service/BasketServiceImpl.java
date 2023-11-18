package com.example.uzum_market.service;

import com.example.uzum_market.dto.AddBasketDTO;
import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.*;
import com.example.uzum_market.repository.BasketRepository;
import com.example.uzum_market.repository.OrderItemRepository;
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
    private final OrderItemRepository orderItemRepository;

    @Override
    public ApiResult<?> saveBasket(AddBasketDTO addBasketDTO) {

        Product product = productRepository.findById(addBasketDTO.getProductId())
                .orElseThrow(() -> RestException.restThrow("Iltimos To'g'ri ma'lumot kiritilganiga etibor bering", HttpStatus.BAD_REQUEST));

        Price price = priceRepository.findById(addBasketDTO.getPriceId())
                .orElseThrow(() -> RestException.restThrow("Iltimos To'g'ri ma'lumot kiritilganiga etibor bering", HttpStatus.BAD_REQUEST));

        User user = CommanUtils.getCurrentUserFromContext();
        Optional<Basket> basketUser = basketRepository.findByUserId(user.getId());
        if (basketUser.isPresent()) {
            Basket basket = basketUser.get();
            OrderItem orderItem = createOrderItem(basket, price);
            return ApiResult.successResponse(orderItem);
        }

        Basket basket = Basket.builder()
                .user(user)
                .build();

        basketRepository.save(basket);
        OrderItem orderItem = createOrderItem(basket, price);

        return ApiResult.successResponse(orderItem);
    }

    private OrderItem createOrderItem(Basket basket, Price price) {
        OrderItem orderItem = OrderItem.builder()
                .basket(basket)
                .color(price.getColor())
                .count(price.getCount())
                .price(price)
                .orderStatus(OrderStatus.BASKET)
                .build();
        orderItemRepository.save(orderItem);
        return orderItem;
    }
}
