package com.calendar.milestone.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class User{
    private int id;
    private String name;
    private String photo;
    private LocalDate birthday;
    private String email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

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
    public LocalDateTime getCreated_at(){return created_at;}
    public LocalDateTime getUpdated_at(){return updated_at;}
    public LocalDateTime getDeleted_at(){return deleted_at;}
    

    public void setId(int id){this.id=id;} 
    public void setName(String name){this.name=name;} 
    public void setPhoto(String photo){this.photo=photo;} 
    public void setBirthday(LocalDate birthday){this.birthday=birthday;} 
    public void setEmail(String email){this.email=email;}  
    public void setCreated_at(LocalDateTime created_at){this.created_at=created_at;} 
    public void setUpdated_at(LocalDateTime updated_at){this.updated_at=updated_at;} 
    public void setDeleted_at(LocalDateTime deleted_at){this.deleted_at=deleted_at;}

}