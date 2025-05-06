package com.calendar.milestone.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Milestone{
    final private int id;
    final private int goal_id;
    final private String title;
    final private String content;
        private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;

    public Milestone(final int id,final int goal_id,final String title,
                    final String content){
        this.id=id;
        this.goal_id=goal_id;
        this.title=title;
        this.content=content;
    }
}