package org.maxkizi.regiondictionary.model.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum RoleType {
    ADMIN("ROLE_ADMIN"),
    GUEST("ROLE_GUEST");

    private String name;

    public static RoleType forName(String name) {
        return Arrays.stream(values())
                .filter(roleType -> roleType.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Не существует роли с таким именем", name)));
    }
}
