package com.calendar.milestone.controller.dto.request.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserEmailChangeRequest {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("currentEmail")
    @NotNull
    @Email
    @Size(min = 6, max = 30)
    private String currentEmail;

    @JsonProperty("newEmail")
    @NotNull
    @Email
    @Size(min = 6, max = 30)
    private String newEmail;


    @JsonProperty("password")
    @NotNull
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\[\\]{};':\"\\\\|,.<>/?]).{8,20}$",
            message = "Password must contain uppercase, lowercase, digit, and special character.")
    private String password;


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getCurrentEmail() {
        return currentEmail;
    }


    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }


    public String getNewEmail() {
        return newEmail;
    }


    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}
