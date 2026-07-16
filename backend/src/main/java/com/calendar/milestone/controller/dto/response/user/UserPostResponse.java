package com.calendar.milestone.controller.dto.response.user;

import com.calendar.milestone.model.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPostResponse {
    @JsonProperty("token")
    private String token;

    @JsonProperty("name")
    private String name;
    
    @JsonProperty("photo")
    private String photo;

    private UserPostResponse(final String token , final String name , final String photo){
        this.token = token;
        this.name = name;
        this.photo = photo;
    }

    public static UserPostResponse from(final LoginResponse loginResponse,final User user){
        return new UserPostResponse(loginResponse.getToken(), user.getName(), user.getPhoto());
    }

    public String getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
