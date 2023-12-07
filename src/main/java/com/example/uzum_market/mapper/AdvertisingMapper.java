package com.example.uzum_market.mapper;

import com.example.uzum_market.dto.SlideDTO;
import com.example.uzum_market.model.Advertising;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdvertisingMapper {
List<SlideDTO> toSlideDTO(List<Advertising> advertising);
}
