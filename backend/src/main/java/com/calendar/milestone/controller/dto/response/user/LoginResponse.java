package com.calendar.milestone.controller.dto.response.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.calendar.milestone.controller.dto.response.common.ApiResponse;


public class LoginResponse implements ApiResponse {
    @JsonProperty("userId")
    private int userId;

    @JsonProperty("token")
    private String token;

    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("statusMessage")
    private String statusMessage;

    public LoginResponse(final int userId, final String token, final Integer statusCode,
            final String statusMessage) {
        this.userId = userId;
        this.token = token;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
}
