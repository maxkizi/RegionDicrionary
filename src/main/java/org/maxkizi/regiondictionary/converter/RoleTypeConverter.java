package org.maxkizi.regiondictionary.converter;

import org.maxkizi.regiondictionary.model.role.RoleType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleTypeConverter implements AttributeConverter<RoleType, String> {
    @Override
    public String convertToDatabaseColumn(RoleType roleType) {
        return roleType.getName();
    }

    @Override
    public RoleType convertToEntityAttribute(String dbData) {
        return RoleType.forName(dbData);
    }
}
