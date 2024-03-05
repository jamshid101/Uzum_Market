package com.example.uzum_market.controller;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {
    private final OrderService orderService;
    @Override
    public ResponseEntity<ApiResult<OrderDTO>> addOrder(AddOrderDTO addOrderDTO) {
        orderService.addOrder(addOrderDTO);
        return null;
    }

    @Override
    public ResponseEntity<ApiResult<Map<OrderStatus,List<OrderForSellerDTO>>>> orderForSeller(MainCriteriaForOrder mainCriteriaForOrder) {
        ApiResult<Map<OrderStatus, List<OrderForSellerDTO>>> result = orderService.orderForSeller(mainCriteriaForOrder);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<ApiResult<String>> changeOrderStatus(ChangeStatusDTO changeStatusDTO) {
        ApiResult<String> result = orderService.changeOrderStatus(changeStatusDTO);
        return ResponseEntity.ok(result);
    }
}
