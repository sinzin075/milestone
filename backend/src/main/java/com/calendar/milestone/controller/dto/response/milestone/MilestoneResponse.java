package com.calendar.milestone.controller.dto.response.milestone;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class MilestoneResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("goalId")
    private Integer goalId;

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

    @JsonProperty("createdAt")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private LocalDateTime updatedAt;

    @JsonProperty("deletedAt")
    private LocalDateTime deletedAt;

    public MilestoneResponse() {}

    public Integer getId() {
        return id;
    }

    public Integer getGoalId() {
        return goalId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
