package com.calendar.milestone.model.config;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

public class JwtSigningKeyConfig {

    private final SecretKey key;

    public JwtSigningKeyConfig(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public SecretKey getKey() {
        return key;
    }
}
