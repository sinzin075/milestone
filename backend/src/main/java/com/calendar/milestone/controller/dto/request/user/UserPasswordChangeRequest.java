package com.calendar.milestone.controller.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserPasswordChangeRequest {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("email")
    @NotNull
    @Email
    @Size(min = 6, max = 30)
    private String email;

    @JsonProperty("currentPassword")
    @NotNull
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$",
            message = "Password must contain uppercase, lowercase, digit, and special character.")
    private String currentPassword;

    @JsonProperty("newPassword")
    @NotNull
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$",
            message = "Password must contain uppercase, lowercase, digit, and special character.")
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}

