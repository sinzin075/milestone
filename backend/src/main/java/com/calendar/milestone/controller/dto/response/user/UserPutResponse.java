package com.calendar.milestone.controller.dto.response.user;

import java.time.LocalDate;

import com.calendar.milestone.model.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserPutResponse {
    @JsonProperty("name")
    private String name;

    @JsonProperty("photo")
    private String photo;

    @JsonProperty("birthday")
    private LocalDate birthday;

    private UserPutResponse(final String name , final String photo , final LocalDate birthday){
        this.name = name;
        this.photo = photo;
        this.birthday = birthday;
    }

    public static UserPutResponse from(final User user){
        return new UserPutResponse(user.getName(), user.getPhoto(), user.getBirthday());
    }

    public String getName() {
        return name;
    }

    public String getPhoto() {
        return photo;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
