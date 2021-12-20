package org.maxkizi.regiondictionary.service.impl;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.security.jwt.JwtConfig;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtService implements IJwtService {

    private final SecretKey key;
    private final JwtConfig jwtConfig;

    @Override
    public String generateAccessToken(Authentication authResult) {
        return Jwts.builder()
                .signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtConfig.getAccessTokenTimeout() * 1000)))
                .claim("authorities", authResult.getAuthorities())
                .setSubject(authResult.getName())
                .compact();
    }

    @Override
    public String generateRefreshToken(Authentication authResult) {
        return Jwts.builder()
                .signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtConfig.getRefreshTokenTimeout() * 1000)))
                .setSubject(authResult.getName())
                .compact();
    }

    @Override
    public void verify(String token) {

    }
}
