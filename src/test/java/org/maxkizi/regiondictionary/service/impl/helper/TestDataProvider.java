package org.maxkizi.regiondictionary.service.impl.helper;

import org.maxkizi.regiondictionary.model.Region;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TestDataProvider {
    public static final String FULL_NAME = "region full name ";
    public static final String SHORT_NAME = "region short name ";
    public static final String REGION_CODE = "10";

    public Region buildRegion(int i) {
        return Region.builder()
                .fullName(FULL_NAME + i)
                .shortName(SHORT_NAME + i)
                .regionCode(REGION_CODE + i)
                .build();
    }
}
