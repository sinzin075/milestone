package com.calendar.milestone.controller.dto.request.goal;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GoalRequest {
    @JsonProperty("id")
    private int id;

    @JsonProperty("userId")
    private int userId;

    @JsonProperty("title")
    @NotNull
    @Size(min = 1, max = 30)
    private String title;

    @JsonProperty("content")
    @NotNull
    @Size(max = 150)
    private String content;

    @JsonProperty("dueDate")
    @NotNull
    private LocalDate dueDate;

    public GoalRequest() {}

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
