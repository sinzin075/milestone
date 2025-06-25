package com.calendar.milestone.model.value;

public class Email {

    private final String email;
    private final int EMAIL_LENGTH_MIN = 6;
    private final int EMAIL_LENGTH_MAX = 30;
    private final String REGEX = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";

    private Email(String email) throws IllegalArgumentException {
        if (email.length() < EMAIL_LENGTH_MIN) {
            throw new IllegalArgumentException("emal too short. email length:" + email.length());
        }
        if (email.length() > EMAIL_LENGTH_MAX) {
            throw new IllegalArgumentException("email too long. email length:" + email.length());
        }
        if (!email.matches(REGEX)) {
            throw new IllegalArgumentException(
                    "The email does not follow the regular expression. this time emal:" + email);
        }
        this.email = email;
    }

    public static Email of(String email) throws IllegalArgumentException {
        return new Email(email);
    }

    public String getValue() {
        return email;
    }

}
