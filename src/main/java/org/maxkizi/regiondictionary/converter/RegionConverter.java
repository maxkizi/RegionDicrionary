package org.maxkizi.regiondictionary.converter;

import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.generated.dto.RegionDto;
import org.maxkizi.regiondictionary.model.Region;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegionConverter {
    private final ModelMapper modelMapper;

    public Region fromDto(RegionDto regionDto) {
        return modelMapper.map(regionDto, Region.class);
    }

    public RegionDto toDto(Region region) {
        return modelMapper.map(region, RegionDto.class);
    }
}
