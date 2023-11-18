package com.example.uzum_market.service;

import com.example.uzum_market.dto.ApiResult;
import com.example.uzum_market.dto.AttachmentDTO;
import com.example.uzum_market.exceptions.RestException;
import com.example.uzum_market.model.Attachment;
import com.example.uzum_market.repository.AttachmentRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.UUID;

import static com.example.uzum_market.utils.CommanUtils.makeFileUrl;


@Service
public record AttachmentServiceImpl(AttachmentRepository attachmentRepository,
                                    Environment environment) implements AttachmentService {

    @Override
    public ApiResult<AttachmentDTO> uploadFile(MultipartHttpServletRequest request) {
        MultipartFile file = getMultipartFileFromRequest(request);

        Attachment attachment = mapAttachment(file);

        Path path = Paths.get(attachment.getContentURL());

        try {
            Files.copy(file.getInputStream(), path);
        } catch (IOException e) {
            throw RestException.restThrow(e.getMessage());
        }

        attachmentRepository.save(attachment);
        return ApiResult.successResponse(mapAttachmentDTO(attachment));
    }

    @Override
    public ResponseEntity<?> downloadFile(Integer id, String view, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(id).orElseThrow(() -> RestException.restThrow("file not found"));
        try {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(attachment.getContentType()))
                    .headers(httpHeaders -> httpHeaders.set("Content-Disposition", view + "; filename=" + attachment.getName()))
                    .body(Files.readAllBytes(Path.of(attachment.getContentURL())));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MultipartFile getMultipartFileFromRequest(MultipartHttpServletRequest request) {

        Iterator<String> fileNames = request.getFileNames();

        if (!fileNames.hasNext())
            throw RestException.restThrow("FILE_NAME_REQUIRED", HttpStatus.BAD_REQUEST);

        MultipartFile file = request.getFile(fileNames.next());

        if (file == null)
            throw RestException.restThrow("FILE NOT FOUND");

        return file;
    }

    private Attachment mapAttachment(MultipartFile file) {

        String originalFilename = file.getOriginalFilename();

        //BU METHOD SYSTEMAGA(PAPKA ICHIGA) FILE UN UNIQUE NAME YASAB BERADI
        assert originalFilename != null;
        String contentType = file.getContentType();
        long size = file.getSize();
        String path = collectFolder(createFileName(originalFilename));

        Attachment attachment = new Attachment();
        attachment.setContentType(contentType);
        attachment.setName(originalFilename);
        attachment.setSize(size);
        attachment.setContentURL(path);

        return attachment;
    }

    private String createFileName(String originalFilename) {
        String name = UUID.randomUUID().toString();
        String[] split = originalFilename.split("\\.");
        String contentType = split[split.length - 1];
        name = name + "." + contentType;
        return name;
    }

    private String collectFolder(String uuidName) {
        LocalDate localDate = LocalDate.now();
        String folderPath =
                environment.getProperty("app.upload.folder")
                        + "/" + localDate.getYear() + "/" + localDate.getMonthValue() + "/" + localDate.getDayOfMonth();
        new File(folderPath).mkdirs();
        return folderPath + "/" + uuidName;
    }

    private AttachmentDTO mapAttachmentDTO(Attachment attachment) {
        return AttachmentDTO.builder()
                .id(attachment.getId())
                .originalName(attachment.getName())
                .url(makeFileUrl(attachment.getId()))
                .build();
    }

}
