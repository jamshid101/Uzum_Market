package com.example.uzum_market.service;

import com.example.uzum_market.dto.*;
import com.example.uzum_market.enums.OrderStatus;

import java.util.List;
import java.util.Map;

public interface OrderService {
    void addOrder(AddOrderDTO addOrderDTO);

    ApiResult<Map<OrderStatus, List<OrderForSellerDTO>>> orderForSeller(MainCriteriaForOrder mainCriteriaForOrder);

    ApiResult<String> changeOrderStatus(ChangeStatusDTO changeStatusDTO);
}
