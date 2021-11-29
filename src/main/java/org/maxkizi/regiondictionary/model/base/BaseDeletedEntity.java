package org.maxkizi.regiondictionary.model.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@SuperBuilder(toBuilder = true)
@MappedSuperclass
@NoArgsConstructor
public class BaseDeletedEntity extends BaseEntity implements IDeletedEntity<Long> {
    @Column(name = "is_deleted")
    private boolean isDeleted;
}
