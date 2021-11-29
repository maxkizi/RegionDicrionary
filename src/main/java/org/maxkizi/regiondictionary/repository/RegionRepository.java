package org.maxkizi.regiondictionary.repository;

import org.maxkizi.regiondictionary.model.QRegion;
import org.maxkizi.regiondictionary.model.Region;
import org.maxkizi.regiondictionary.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends BaseRepository<Region, Long, QRegion> {
}
