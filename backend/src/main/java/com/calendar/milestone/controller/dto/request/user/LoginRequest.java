package com.calendar.milestone.controller.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class LoginRequest {
    @JsonProperty("email")
    @NotNull
    @Email
    @Size(min = 6, max = 30)
    private String email;

    @JsonProperty("password")
    @NotNull
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$",
            message = "Password must contain uppercase, lowercase, digit, and special character.")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
