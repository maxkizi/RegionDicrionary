package org.maxkizi.regiondictionary.model.role;

import lombok.Data;
import org.maxkizi.regiondictionary.converter.RoleTypeConverter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class RolePK implements Serializable {

    @Column(name = "code")
    @Convert(converter = RoleTypeConverter.class)
    private RoleType code;
}
