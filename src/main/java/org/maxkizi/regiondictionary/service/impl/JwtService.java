package org.maxkizi.regiondictionary.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.security.jwt.JwtConfig;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class JwtService implements IJwtService {

    private final SecretKey key;
    private final JwtConfig jwtConfig;

    @Override
    public String generateAccessToken(Authentication authResult) {
        return Jwts.builder()
                .signWith(key)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + (jwtConfig.getAccessTokenTimeout() * 1000 * 60)))
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

        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");
        System.out.println();
        Collection<SimpleGrantedAuthority> authority = authorities.stream().map(map -> new SimpleGrantedAuthority(map.get("authority"))).collect(Collectors.toSet());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                body.getSubject(),
                null,
                authority
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
