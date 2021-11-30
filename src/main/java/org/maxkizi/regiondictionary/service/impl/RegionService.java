package org.maxkizi.regiondictionary.service.impl;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.exception.RegionNotFoundException;
import org.maxkizi.regiondictionary.model.QRegion;
import org.maxkizi.regiondictionary.model.Region;
import org.maxkizi.regiondictionary.repository.RegionRepository;
import org.maxkizi.regiondictionary.service.IRegionService;
import org.maxkizi.regiondictionary.service.base.AbstractBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Data
public class RegionService extends AbstractBaseService<Region, Long, QRegion, RegionRepository> implements IRegionService {

    private final RegionRepository repository;

    @Override
    public Page<Region> list(Pageable pageable, String search) {
        BooleanBuilder bb = new BooleanBuilder();
        if (StringUtils.hasText(search)) {
            bb.or(QRegion.region.fullName.containsIgnoreCase(search));
            bb.or(QRegion.region.shortName.containsIgnoreCase(search));
            bb.or(QRegion.region.regionCode.containsIgnoreCase(search));
        }
        bb.and(QRegion.region.isDeleted.isFalse());
        return findAll(bb, pageable);
    }

    @Override
    public Region findById(Long id) {
        BooleanBuilder bb = new BooleanBuilder();
        bb.and(QRegion.region.isDeleted.isFalse()).and(QRegion.region.id.eq(id));
        return get(bb).orElseThrow(RegionNotFoundException::new);
    }

    @Override
    public Region create(Region region) {
        return save(region);
    }

    @Override
    public Region update(Long id, Region region) {
        Region oldRegion = get(id).orElseThrow(RegionNotFoundException::new);
        region.setId(id);
        region.setCreatedAt(oldRegion.getCreatedAt());
        return save(region);
    }

    @Override
    public void delete(Long id) {
        Region region = get(id).orElseThrow(RegionNotFoundException::new);
        region.setDeleted(true);
        save(region);
    }
}
