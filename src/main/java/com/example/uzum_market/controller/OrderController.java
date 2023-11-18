package com.example.uzum_market.controller;

import com.example.uzum_market.dto.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = OrderController.BASE_PATH)
public interface OrderController {

    String BASE_PATH = "/api/order";
    String UPLOAD_PATH = "/user-or";

    @PostMapping
    ResponseEntity<?> order(@RequestBody List<OrderDTO> orderDTOs);

}
