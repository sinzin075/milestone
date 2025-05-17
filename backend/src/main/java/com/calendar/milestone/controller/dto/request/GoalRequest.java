package com.calendar.milestone.controller.dto.requesto;

import java.time.LocalDate;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GoalRequest {
    @NotNull @Size(min=1,max=30)
    private String title;
    @NotNull @Size(max=150)
    private String content;
    @NotNull 
    private LocalDate date;

    public String getTitle(){return title;}
    public String getContent(){return content;}
    public LocalDate getDate(){return date;}

    public void setTitle(String title){this.title=title;} 
    public void setContent(String content){this.content=content;} 
    public void setDate(LocalDate date){this.date=date;}   
}
