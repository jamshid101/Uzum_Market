package com.example.uzum_market.controller;

import com.example.uzum_market.dto.AttachmentDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RequestMapping(value = AttachmentController.BASE_PATH)
public interface AttachmentController {

    String BASE_PATH = "/api/attachment/";
    String UPLOAD_PATH = "upload";

    @PostMapping(UPLOAD_PATH)
    ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request);


    @GetMapping("{id}")
    ResponseEntity<?> downloadFile(@PathVariable Integer id,
                                   @RequestParam(defaultValue = "inline") String view,
                                   HttpServletResponse response);
}
