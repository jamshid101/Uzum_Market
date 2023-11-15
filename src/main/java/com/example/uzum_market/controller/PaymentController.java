package com.example.uzum_market.controller;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.AttachmentDTO;
import com.example.uzum_market.dto.FillBalanceDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping(value = PaymentController.BASE_PATH)
public interface PaymentController {

    String BASE_PATH = "/api/payment/";
    String FILL_BALANCE_PATH = "fill-balance";

    @PostMapping(FILL_BALANCE_PATH)
    HttpEntity<ApiResult<?>> fillBalance(@RequestBody FillBalanceDTO fillBalanceDTO);


}
