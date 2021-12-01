package org.maxkizi.regiondictionary.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.converter.RegionConverter;
import org.maxkizi.regiondictionary.service.IRegionService;
import org.maxkizi.userdemo.generated.dto.RegionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.maxkizi.regiondictionary.controller.ControllerUtils.REGION_CONTROLLER;
import static org.maxkizi.regiondictionary.controller.ControllerUtils.REGION_CONTROLLER_BY_ID;

@RestController
@RequiredArgsConstructor
public class RegionController {
    private final IRegionService service;
    private final RegionConverter converter;


    @GetMapping(REGION_CONTROLLER)
    public Page<RegionDto> list(Pageable pageable,
                                @RequestParam(name = "search", required = false) String search) {
        return service.list(pageable, search).map(converter::toDto);
    }

    @GetMapping(REGION_CONTROLLER_BY_ID)
    public RegionDto findById(@PathVariable(name = "id") Long id) {
        return converter.toDto(service.findById(id));
    }

    @PostMapping(REGION_CONTROLLER)
    public RegionDto create(@RequestBody @Valid RegionDto regionDto) {
        return converter.toDto(service.create(converter.fromDto(regionDto)));
    }

    @PutMapping(REGION_CONTROLLER_BY_ID)
    public RegionDto update(@PathVariable(name = "id") Long id,
                            @RequestBody @Valid RegionDto regionDto) {
        return converter.toDto(service.update(id, converter.fromDto(regionDto)));
    }

    @DeleteMapping(REGION_CONTROLLER_BY_ID)
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
