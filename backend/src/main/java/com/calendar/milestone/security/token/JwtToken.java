package com.calendar.milestone.security.token;


public class JwtToken {
    private final String token;
    private static final int EXPECTED_DOT_COUNT = 2;
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

    private JwtToken(final JwtTokenGenerator token) throws IllegalArgumentException {
        final String rawToken = token.getRawToken();
        this.token = validDotCount(rawToken);
    }

    private JwtToken(final String token) throws IllegalArgumentException {
        final String rawToken = clientTokenCheck(token);
        this.token = validDotCount(rawToken);
    }

    private String validDotCount(final String token) {
        final int dotCount = (int) token.chars().filter(cha -> cha == '.').count();
        if (dotCount != EXPECTED_DOT_COUNT) {
            throw new IllegalArgumentException(
                    "The token format is invalid. Expected 2 periods (JWT format).");
        }
        return token;
    }

    private String clientTokenCheck(final String token) throws IllegalArgumentException {
        if (!token.startsWith(BEARER_PREFIX)) {
            throw new IllegalArgumentException(
                    "The token format is invalid. Expected a Bearer token.");
        }
        return token.substring(BEARER_PREFIX_LENGTH);

    }

    public static JwtToken fromGenerator(final JwtTokenGenerator token)
            throws IllegalArgumentException {
        return new JwtToken(token);
    }

    public static JwtToken fromClientToken(final String rawToken) throws IllegalArgumentException {
        return new JwtToken(rawToken);
    }

    public String getToken() {
        return token;
    }

}
