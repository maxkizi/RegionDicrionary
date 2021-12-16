package org.maxkizi.regiondictionary;

import org.maxkizi.regiondictionary.security.jwt.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@EnableConfigurationProperties(JwtConfig.class)
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class);
    }
}
