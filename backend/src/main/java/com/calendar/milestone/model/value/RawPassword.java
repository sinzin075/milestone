package com.calendar.milestone.model.value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RawPassword {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final String rawPassword;
    private final int PASSWORD_LENGTH_MIN = 8;
    private final int PASSWORD_LENGTH_MAX = 20;
    private final String REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]).{"
                    + PASSWORD_LENGTH_MIN + "," + PASSWORD_LENGTH_MAX + "}$";


    private RawPassword(final String rawPassword) {
        if (rawPassword.length() < PASSWORD_LENGTH_MIN) {
            throw new IllegalArgumentException("Argument too short");
        }
        if (rawPassword.length() > PASSWORD_LENGTH_MAX) {
            throw new IllegalArgumentException("Argument too long");
        }
        if (!rawPassword.matches(REGEX)) {
            throw new IllegalArgumentException("Not matched this password to regex");
        }
        this.rawPassword = rawPassword;
    }

    public static RawPassword of(final String rawPassword) {
        return new RawPassword(rawPassword);
    }

    public boolean passwordMatch(final String passwordEncoded) {
        return passwordEncoder.matches(rawPassword, passwordEncoded);
    }


}
