package org.maxkizi.regiondictionary.security.jwt;

import com.google.common.net.HttpHeaders;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "application.jwt")
@NoArgsConstructor
@Data
public class JwtConfig {
    private String secretKey;
    private String refreshTokenHeader;
    private String accessTokenHeader;
    private String tokenPrefix;
    private Long accessTokenTimeout;
    private Long refreshTokenTimeout;

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
