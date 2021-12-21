package org.maxkizi.regiondictionary.security.jwt;

import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.maxkizi.regiondictionary.service.impl.JwtService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtTokenVerifier extends OncePerRequestFilter {
    private final JwtService jwtService;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!bearerToken.startsWith(jwtService.getJwtConfig().getTokenPrefix()) || Strings.isNullOrEmpty(bearerToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = bearerToken.replace(jwtService.getJwtConfig().getTokenPrefix(), "");
        jwtService.verify(jwtToken);
        filterChain.doFilter(request, response);
    }
}
