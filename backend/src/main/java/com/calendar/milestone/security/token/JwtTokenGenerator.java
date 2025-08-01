package com.calendar.milestone.security.token;

import java.util.Date;
import com.calendar.milestone.model.config.JwtSigningKeyConfig;
import com.calendar.milestone.model.value.JwtPayload;
import io.jsonwebtoken.Jwts;

public class JwtTokenGenerator {
    private static final int ONE_HOUR = 3600 * 1000;
    private final String token;

    private JwtTokenGenerator(final String token) {
        this.token = token;
    }

    public static JwtToken generateToken(final JwtSigningKeyConfig key,
            final JwtPayload jwtPayload) {
        String generatoToken = Jwts.builder().subject(jwtPayload.getPayloadId())
                .issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + ONE_HOUR))
                .signWith(key.getKey()).compact();
        return JwtToken.fromGenerator(new JwtTokenGenerator(generatoToken));
    }

    public String getRawToken() {
        return token;
    }
}
