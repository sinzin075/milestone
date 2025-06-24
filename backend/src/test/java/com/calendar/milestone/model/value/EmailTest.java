package com.calendar.milestone.model.value;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailTest {

    @Test
    void validEmail_shouldCreateInstance() {
        Email email = Email.of("user@example.com");
        assertEquals("user@example.com", email.getEmail());
    }

    @Test
    void tooShortEmail_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Email.of("a@b.c");
        });
        assertTrue(exception.getMessage().contains("too short"));
    }

    @Test
    void tooLongEmail_shouldThrowException() {
        String longEmail = "a".repeat(50) + "@example.com";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Email.of(longEmail);
        });
        assertTrue(exception.getMessage().contains("too long"));
    }

    @Test
    void invalidRegexEmail_shouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Email.of("not-an-email");
        });
        assertTrue(exception.getMessage().contains("regular expression"));
    }
}
