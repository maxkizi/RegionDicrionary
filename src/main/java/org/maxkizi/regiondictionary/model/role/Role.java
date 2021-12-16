package org.maxkizi.regiondictionary.model.role;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "roles")
@Entity
@Data
public class Role {
    @EmbeddedId()
    private RolePK id;

    @Column(name = "description")
    private String description;

    public String getRoleName() {
        return id.getCode().getName();
    }
}
