package com.calendar.milestone.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class User{
    private int id;
    private String name;
    private String photo;
    private LocalDate birthday;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public User(){}

    public User(final int id,final String name,final String photo,
                final LocalDate birthday,final String email){
        this.id=id;
        this.name=name;
        this.photo=photo;
        this.birthday=birthday;
        this.email=email;
    }

    public int getId(){return id;}
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