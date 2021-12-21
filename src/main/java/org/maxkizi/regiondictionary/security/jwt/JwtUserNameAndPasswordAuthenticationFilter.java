package org.maxkizi.regiondictionary.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.service.impl.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtUserNameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UserNameAndPasswordAuthenticationRequest userNameAndPassword =
                    new ObjectMapper().readValue(request.getInputStream(), UserNameAndPasswordAuthenticationRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userNameAndPassword.getUsername(), userNameAndPassword.getPassword()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String refreshToken = jwtService.generateRefreshToken(authResult);
        String accessToken = jwtService.generateAccessToken(authResult);
        response.addHeader(jwtService.getJwtConfig().getAccessTokenHeader(), jwtService.getJwtConfig().getTokenPrefix() + accessToken);
        response.addHeader(jwtService.getJwtConfig().getRefreshTokenHeader(), jwtService.getJwtConfig().getTokenPrefix() + refreshToken);

    }
}
