package com.calendar.milestone.model.value;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Password {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    final String encodedPassword;
    private final int PASSWORD_LENGTH_MIN = 8;
    private final int PASSWORD_LENGTH_MAX = 20;
    private final String REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]).{"
                    + PASSWORD_LENGTH_MIN + "," + PASSWORD_LENGTH_MAX + "}$";

    private Password(String defaultPassword) throws IllegalArgumentException {
        if (defaultPassword.length() < PASSWORD_LENGTH_MIN) {
            throw new IllegalArgumentException("Argument too short");
        }
        if (defaultPassword.length() > PASSWORD_LENGTH_MAX) {
            throw new IllegalArgumentException("Argument too long");
        }
        if (!defaultPassword.matches(REGEX)) {
            throw new IllegalArgumentException("Not matched this password to regex");
        }
        encodedPassword = passwordEncoder.encode(defaultPassword);

    }

    public static Password encode(String defaultPassword) {
        return new Password(defaultPassword);
    }

    public String getEncordedPassword() {
        return encodedPassword;
    }

}
