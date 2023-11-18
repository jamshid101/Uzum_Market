package com.example.uzum_market.controller;


import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.AttachmentDTO;
import com.example.uzum_market.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
public record AttachmentControllerImpl(AttachmentService attachmentService) implements AttachmentController {


    @Override
    public ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request) {
        return attachmentService.uploadFile(request);
    }

    @Override
    public ResponseEntity<?> downloadFile(Integer id, String view, HttpServletResponse response) {
        return attachmentService.downloadFile(id, view, response);
    }
}
