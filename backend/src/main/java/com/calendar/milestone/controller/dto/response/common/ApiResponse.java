package com.calendar.milestone.controller.dto.response.common;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ApiResponse<T> {
    @JsonProperty
    private T data;
    @JsonProperty
    private ApiStatus apiStatus; 

    public ApiResponse(final T data,final ApiStatus apiStatus){
        this.data = data;
        this.apiStatus = apiStatus;
    }
}
