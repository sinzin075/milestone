package com.calendar.milestone.controller.dto.request.milestone;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class MilestoneRequest {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("goal_id")
    private int goalId;

    @JsonProperty("title")
    @NotNull
    @Size(min = 1, max = 30)
    private String title;

    @JsonProperty("content")
    @NotNull
    @Size(max = 150)
    private String content;

    @JsonProperty("date")
    @NotNull
    private LocalDate date;

    public MilestoneRequest() {}

    public int getId() {
        return id;
    }

    public int getGoalId() {
        return goalId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
