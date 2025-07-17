package com.calendar.milestone.controller.dto.response.user;

import com.calendar.milestone.controller.dto.response.common.ApiResponse;
import com.calendar.milestone.controller.dto.response.common.ApiStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserApiStatusResponse implements ApiResponse {

    @JsonProperty("statusCode")
    private Integer statusCode;

    @JsonProperty("statusMessage")
    private String statusMessage;

    public UserApiStatusResponse(final ApiStatus api) {
        statusCode = api.getStatus();
        statusMessage = api.getMessage();
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
