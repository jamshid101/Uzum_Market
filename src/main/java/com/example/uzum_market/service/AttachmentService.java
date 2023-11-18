package com.example.uzum_market.service;

import com.example.uzum_market.dto.AttachmentDTO;
import com.example.uzum_market.dto.ApiResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface AttachmentService {

    ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request);

    ResponseEntity<?> downloadFile(Integer id, String view, HttpServletResponse response);
}
