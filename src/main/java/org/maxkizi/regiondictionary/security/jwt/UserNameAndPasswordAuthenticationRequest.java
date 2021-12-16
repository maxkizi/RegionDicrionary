package org.maxkizi.regiondictionary.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserNameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
