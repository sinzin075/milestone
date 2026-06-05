package com.calendar.milestone.controller.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginResponse  {
    @JsonProperty("token")
    private String token;

    public LoginResponse(final String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
