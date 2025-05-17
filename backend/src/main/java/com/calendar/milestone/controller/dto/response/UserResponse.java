package com.calendar.milestone.controller.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    @NotNull
    @Size(min=1,max=20)
    private String name;
    
    @JsonProperty("photo")
    @Size(max=200)
    private String photo;
    
    @JsonProperty("birthday")
    private LocalDate birthday;
    
    @JsonProperty("email")
    @NotNull
    @Email
    @Size(max=30)
    private String email;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    public Integer getId(){return id;}
    public String getName(){return name;}
    public String getPhoto(){return photo;}
    public LocalDate getBirthday(){return birthday;}
    public String getEmail(){return email;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public LocalDateTime getUpdatedAt(){return updatedAt;}
    public LocalDateTime getDeletedAt(){return deletedAt;}

    public void setId(int id){this.id=id;}
    public void setName(String name){this.name=name;} 
    public void setPhoto(String photo){this.photo=photo;} 
    public void setBirthday(LocalDate birthday){this.birthday=birthday;} 
    public void setEmail(String email){this.email=email;}  
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt=createdAt;} 
    public void setUpdatedAt(LocalDateTime updatedAt){this.updatedAt=updatedAt;} 
    public void setDeletedAt(LocalDateTime deletedAt){this.deletedAt=deletedAt;}
}
