package com.calendar.milestone.controller.dto.request.user;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;

public class UserPutRequest {
    private Integer id;

    @JsonProperty("name")
    @Size(min = 1, max = 20)
    private String name;

    @JsonProperty("photo")
    @Size(max = 200)
    private String photo;

    @JsonProperty("birthday")
    private LocalDate birthday;

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

    public void setId(Integer id) {
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
}
