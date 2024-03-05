package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.enums.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(value = OrderController.BASE_PATH)
public interface OrderController {

    String BASE_PATH = "/api/order";
    String ADD_ORDER_PATH = "/add-order";
    String SELLER_ORDER_PATH = "/order-seller";
    String CHANGE_ORDER_PATH = "/order-seller";

    @PostMapping(ADD_ORDER_PATH)
    ResponseEntity<ApiResult<OrderDTO>> addOrder(@RequestBody AddOrderDTO addOrderDTO);

    @PostMapping(SELLER_ORDER_PATH)
    ResponseEntity<ApiResult<Map<OrderStatus, List<OrderForSellerDTO>>>> orderForSeller(@RequestBody MainCriteriaForOrder mainCriteriaForOrder);
    @PostMapping(CHANGE_ORDER_PATH)
    ResponseEntity<ApiResult<String>> changeOrderStatus(@RequestBody ChangeStatusDTO changeStatusDTO);

}
