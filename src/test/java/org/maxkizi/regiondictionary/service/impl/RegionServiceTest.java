package org.maxkizi.regiondictionary.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maxkizi.regiondictionary.exception.RegionNotFoundException;
import org.maxkizi.regiondictionary.model.Region;
import org.maxkizi.regiondictionary.repository.RegionRepository;
import org.maxkizi.regiondictionary.service.IRegionService;
import org.maxkizi.regiondictionary.service.impl.helper.TestDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class RegionServiceTest extends BaseIntegrationTest {
    private final IRegionService regionService;
    private final TestDataProvider testDataProvider;
    private final RegionRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public RegionServiceTest(IRegionService regionService, TestDataProvider testDataProvider, RegionRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.regionService = regionService;
        this.testDataProvider = testDataProvider;
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @BeforeEach
    void deleteAll() {
        repository.deleteAll();
    }

    /**
     * Позитивный сценарий
     * --------------------------------------------------------------------------------------
     */

    @Test
    void shouldCreateAndList() {
        regionService.create(testDataProvider.buildRegion(2));
        regionService.create(testDataProvider.buildRegion(1));

        Page<Region> list = regionService.list(PageRequest.of(0, 2), null);
        Assertions.assertEquals(2, list.getSize());
        list.forEach(region -> {
            Assertions.assertFalse(region.isDeleted());
        });

        Region region1 = regionService.list(PageRequest.of(0, 1), "full name 1").getContent().get(0);
        Assertions.assertEquals(TestDataProvider.FULL_NAME + 1, region1.getFullName());
        Assertions.assertEquals(TestDataProvider.SHORT_NAME + 1, region1.getShortName());
        Assertions.assertEquals(TestDataProvider.REGION_CODE + 1, region1.getRegionCode());
        System.out.println(region1);

        Region region2 = regionService.list(PageRequest.of(0, 1), "short name 2").getContent().get(0);
        System.out.println(region2);
        Assertions.assertEquals(TestDataProvider.FULL_NAME + 2, region2.getFullName());
        Assertions.assertEquals(TestDataProvider.SHORT_NAME + 2, region2.getShortName());
        Assertions.assertEquals(TestDataProvider.REGION_CODE + 2, region2.getRegionCode());
    }

    @Test
    void shouldCreateAndFindById() {
        Region createdRegion = regionService.create(testDataProvider.buildRegion(1));
        Region foundRegion = regionService.findById(createdRegion.getId());
        Assertions.assertEquals(createdRegion, foundRegion);
    }

    @Test
    void shouldCreateAndUpdate() {
        String newRegionCode = "7777";
        Region createdRegion = regionService.create(testDataProvider.buildRegion(1));
        Region foundRegion = regionService.findById(createdRegion.getId());
        foundRegion.setRegionCode(newRegionCode);
        Region updatedRegion = regionService.update(foundRegion.getId(), foundRegion);
        Assertions.assertEquals(updatedRegion.getRegionCode(), newRegionCode);
    }

    @Test
    void shouldCreateAndDelete() {
        Region createdRegion = regionService.create(testDataProvider.buildRegion(1));
        Region foundRegion = regionService.findById(createdRegion.getId());
        regionService.delete(foundRegion.getId());
        boolean isDeleted = repository.findById(foundRegion.getId()).get().isDeleted();
        Assertions.assertEquals(true, isDeleted);
    }

    /**
     * Негативный сценарий
     * -----------------------------------------------------------------------------------------
     */

    @Test
    void findByIdAndThrowRegionNotFound() {
        Assertions.assertThrows(RegionNotFoundException.class, () -> regionService.findById(1L));
    }

    @Test
    void deleteAndThrowRegionNotFound() {
        Assertions.assertThrows(RegionNotFoundException.class, () -> regionService.delete(1L));
    }

    @Test
    void updateAndFindByIdAndThrowRegionNotFound() {
        Assertions.assertThrows(RegionNotFoundException.class, () -> regionService.update(1L, new Region()));
    }

    @Test
    void passwordEncode(){
        System.out.println("--------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(passwordEncoder.encode("guest"));
    }
}