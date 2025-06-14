package com.calendar.milestone.controller.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserRequest {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    @JsonProperty("photo")
    @Size(max = 200)
    private String photo;

    @JsonProperty("birthday")
    private LocalDate birthday;

    @JsonProperty("email")
    @NotNull
    @Email
    @Size(max = 30)
    private String email;

    @JsonProperty("password")
    @NotNull
    @Size(min = 8, max = 200)
    private String password;

    public Integer getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
