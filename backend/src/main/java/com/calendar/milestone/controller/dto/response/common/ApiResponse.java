package com.calendar.milestone.controller.dto.response.common;

public interface ApiResponse {
    Integer getStatusCode();

    void setStatusCode(Integer statusCode);

    String getStatusMessage();

    void setStatusMessage(String statusMessage);
}
