package org.maxkizi.regiondictionary.service;

import org.maxkizi.regiondictionary.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IRegionService {

    Page<Region> list(Pageable pageable, String search);

    Region findById(Long id);

    Region create(Region region);

    Region update(Long id, Region region);

    void delete(Long id);
}
