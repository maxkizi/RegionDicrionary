package org.maxkizi.regiondictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.maxkizi.regiondictionary.model.base.BaseDeletedEntity;
import org.maxkizi.regiondictionary.model.base.IEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "region_dictionary")
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
public class Region extends BaseDeletedEntity {
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "short_name")
    private String shortName;
    @Column (name = "region_code")
    private String regionCode;
}
