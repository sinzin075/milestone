package com.calendar.milestone.security.token;

public class JwtToken {
    private final String token;
    private static final int EXPECTED_DOT_COUNT = 2;
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int BEARER_PREFIX_LENGTH = BEARER_PREFIX.length();

    private JwtToken(final String token) throws IllegalArgumentException {

        final String cleanToken = cloudTokenCheck(token);
        if (!tokenCheck(cleanToken)) {
            throw new IllegalArgumentException("Token period mismatch");
        }
        this.token = cleanToken;
    }

    private boolean tokenCheck(String token) {
        final int keyWord = (int) token.chars().filter(cha -> cha == '.').count();
        if (keyWord != EXPECTED_DOT_COUNT) {
            return false;
        }
        return true;
    }

    private String cloudTokenCheck(String token) {
        if (token.startsWith(BEARER_PREFIX))
            return token.substring(BEARER_PREFIX_LENGTH);
        return token;
    }

    public static JwtToken of(final String token) throws IllegalArgumentException {
        return new JwtToken(token);
    }

    public String getToken() {
        return token;
    }

}
