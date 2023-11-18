package com.example.uzum_market.controller;

import com.example.uzum_market.dto.OrderHistoryDTO;
import com.example.uzum_market.dto.PaymentHistoryDTO;
import com.example.uzum_market.enums.OrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = HistoryController.BASE_PATH)
public interface HistoryController {

    String BASE_PATH = "/api/history";
    String ORDER_PATH = "/order";
    String PAYMENT_PATH = "/payment";


    @GetMapping(ORDER_PATH)
    ResponseEntity<ApiResult<List<OrderHistoryDTO>>> showOrderHistory(@RequestBody OrderStatus orderStatus);

    @GetMapping(PAYMENT_PATH)
    ResponseEntity<ApiResult<List<PaymentHistoryDTO>>> showPaymentHistory();

}
