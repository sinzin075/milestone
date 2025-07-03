package com.calendar.milestone.model.utility;

import java.util.Date;
import com.calendar.milestone.model.value.JwtPayload;
import io.jsonwebtoken.Jwts;

public class JwtUtility {
    static private final int ONE_HOUR = 3600 * 1000;
    final private String token;

    private JwtUtility(final JwtPayload jwtPayload) {
        token = Jwts.builder().subject(jwtPayload.getPayloadId()).issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ONE_HOUR)).compact();
    }

    public static JwtUtility of(final JwtPayload jwtPayload) {
        return new JwtUtility(jwtPayload);
    }

    public String getToken() {
        return token;
    }
}
