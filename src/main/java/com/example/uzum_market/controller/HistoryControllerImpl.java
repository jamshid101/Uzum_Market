package com.example.uzum_market.controller;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.OrderHistoryDTO;
import com.example.uzum_market.dto.PaymentHistoryDTO;
import com.example.uzum_market.enums.OrderStatus;
import com.example.uzum_market.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequiredArgsConstructor
public class HistoryControllerImpl implements HistoryController {

    private final HistoryService historyService;

    @Override
    public HttpEntity<ApiResult<List<OrderHistoryDTO>>> showOrderHistory(OrderStatus orderStatus) {
      List<OrderHistoryDTO> orderHistoryDTOS = historyService.showOrderHistory(orderStatus);
        return ResponseEntity.ok(ApiResult.successResponse(orderHistoryDTOS));
    }

    @Override
    public HttpEntity<ApiResult<List<PaymentHistoryDTO>>> showPaymentHistory() {
         List<PaymentHistoryDTO> paymentHistoryDTOS = historyService.showPaymentHistory();
        return ResponseEntity.ok(ApiResult.successResponse(paymentHistoryDTOS));
    }
}
