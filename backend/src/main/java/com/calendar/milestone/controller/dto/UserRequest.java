package com.calendar.milestone.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRequest {
    @NotNull @Size(min=1,max=20)
    private String name;
    @Size(max=200)
    private String photo;
    private LocalDate birthday;
    @NotNull @Email @Size(max=30)
    private String email;

    public String getName(){return name;}
    public String getPhoto(){return photo;}
    public LocalDate getBirthday(){return birthday;}
    public String getEmail(){return email;}

    public void setName(String name){this.name=name;} 
    public void setPhoto(String photo){this.photo=photo;} 
    public void setBirthday(LocalDate birthday){this.birthday=birthday;} 
    public void setEmail(String email){this.email=email;}  
}
