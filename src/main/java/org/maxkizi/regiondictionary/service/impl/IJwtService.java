package org.maxkizi.regiondictionary.service.impl;

import org.springframework.security.core.Authentication;

public interface IJwtService {
    String generateAccessToken(Authentication authResult);

    String generateRefreshToken(Authentication authResult);

    void verify(String token);
}
