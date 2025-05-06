package com.calendar.milestone.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Goal{
    private int id;
    private int user_id;
    private String title;
    private String content;
    private LocalDate date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;


    public Goal(final int id,final int user_id,final String title,
                final String content,final LocalDate date){
        this.id=id;
        this.user_id=user_id;
        this.title=title;
        this.content=content;
        this.date=date;
    }
    public Goal(){}

    public int getId(){return id;}
    public int getUser_id(){return user_id;}
    public String getTitle(){return title;}
    public String getContent(){return content;}
    public LocalDate getDate(){return date;}
    public LocalDateTime getCreated_at(){return created_at;}
    public LocalDateTime getUpdated_at(){return updated_at;}
    public LocalDateTime getDeleted_at(){return deleted_at;}

    public void setId(int id){this.id=id;}
    public void setUser_id(int id){this.user_id=id;}
    public void setTitle(String title){this.title=title;}
    public void setContent(String content){this.content=content ;}
    public void setDate(LocalDate date){this.date=date;}
    public void setCreated_at(LocalDateTime created_at){this.created_at=created_at;} 
    public void setUpdated_at(LocalDateTime updated_at){this.updated_at=updated_at;} 
    public void setDeleted_at(LocalDateTime deleted_at){this.deleted_at=deleted_at;}
}