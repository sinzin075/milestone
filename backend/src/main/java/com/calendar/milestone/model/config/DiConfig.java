package com.calendar.milestone.model.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

@Configuration
public class DiConfig {

    @Bean
    public JwtSigningKeyConfig jwtSigningKeyConfig(@Value("${jwt.secret}") final String secret) {
        return new JwtSigningKeyConfig(secret);
    }

    @Bean
    public JwtDecoder jwtDecoder(final JwtSigningKeyConfig jwtSigningKeyConfig) {
        return NimbusJwtDecoder.withSecretKey(jwtSigningKeyConfig.getKey()).build();
    }

    @Bean
    public AuthenticationManager authenticatorManager(final JwtDecoder jwtDecoder) {
        return new ProviderManager(new JwtAuthenticationProvider(jwtDecoder));
    }
}
