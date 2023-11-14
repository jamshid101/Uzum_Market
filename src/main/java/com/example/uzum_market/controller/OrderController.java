package com.example.uzum_market.controller;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.AttachmentDTO;
import com.example.uzum_market.dto.OrderDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@RequestMapping(value = OrderController.BASE_PATH)
public interface OrderController {

    String BASE_PATH = "/api/order";
    String UPLOAD_PATH = "/user-or";

    @PostMapping
    ResponseEntity<?> order(@RequestBody List<OrderDTO> orderDTOs);

}
