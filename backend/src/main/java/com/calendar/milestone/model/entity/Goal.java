package com.calendar.milestone.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Goal{
    private int id;
    private int user_id;
    private String title;
    private String content;
    private LocalDate date;
    

    public Goal(final int id,final int user_id,final String title,
                final String content,final LocalDate date){
        this.id=id;
        this.user_id=user_id;
        this.title=title;
        this.content=content;
        this.date=date;
    }

    public int getId(){return id;}
    public int getUser_id(){return user_id;}
    public String getTitle(){return title;}
    public String getContent(){return content;}
    public LocalDate getDate(){return date;}

    public setTitle(String title){this.title=title;}
    public setContent(String content){this.content=content ;}
    public setdate(LocalDate date){this.date=date;}
    public set(){return ;}
    public set(){return ;}
}