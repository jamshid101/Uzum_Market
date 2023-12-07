package com.example.uzum_market.mapper;

import com.example.uzum_market.dto.AttachmentDTO;
import com.example.uzum_market.model.Attachment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttachmentMapper {
    List<AttachmentDTO> toAttachmentDTOList(List<Attachment> attachments);

    AttachmentDTO toAttachmentDTO(Attachment attachments);
}
