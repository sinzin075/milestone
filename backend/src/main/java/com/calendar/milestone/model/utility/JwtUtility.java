package com.calendar.milestone.model.utility;

import java.util.Date;
import com.calendar.milestone.model.config.JwtSigningKeyConfig;
import com.calendar.milestone.model.value.JwtPayload;
import io.jsonwebtoken.Jwts;

public class JwtUtility {
    private static final int ONE_HOUR = 3600 * 1000;
    private final String token;
    private final JwtSigningKeyConfig key;


    private JwtUtility(final JwtSigningKeyConfig key, final JwtPayload jwtPayload) {
        this.key = key;
        token = Jwts.builder().subject(jwtPayload.getPayloadId()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ONE_HOUR)).signWith(key.getKey())
                .compact();
    }

    public static JwtUtility of(final JwtSigningKeyConfig key, final JwtPayload jwtPayload) {
        return new JwtUtility(key, jwtPayload);
    }

    public String getToken() {
        return token;
    }
}
