package com.calendar.milestone.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiConfig {

    @Bean
    public JwtSigningKeyConfig jwtSigningKeyConfig(@Value("${jwt.secret}") final String secret) {
        return new JwtSigningKeyConfig(secret);
    }
}
