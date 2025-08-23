package com.calendar.milestone.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import com.calendar.milestone.model.config.JwtSigningKeyConfig;

@Configuration
@EnableWebSecurity
public class DiConfig {

    @Bean
    public JwtSigningKeyConfig jwtSigningKeyConfig(@Value("${jwt.secret}") final String secret) {
        return new JwtSigningKeyConfig(secret);
    }

    @Bean
    public JwtDecoder jwtDecoder(final JwtSigningKeyConfig jwtSigningKeyConfig) {
        // 64バイト以上のアルゴリズムでトークン発行をしているためデコード側でも対応するものを設定(HS512)
        return NimbusJwtDecoder.withSecretKey(jwtSigningKeyConfig.getKey())
                .macAlgorithm(MacAlgorithm.HS512).build();
    }

    // JwtAuthenticationProviderにてクレームセットを返している。
    @Bean
    public AuthenticationManager authenticatorManager(final JwtDecoder jwtDecoder) {
        return new ProviderManager(new JwtAuthenticationProvider(jwtDecoder));
    }
}
