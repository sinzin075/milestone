package com.calendar.milestone.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Goal {
    private int id;
    private int userId;
    private String title;
    private String content;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;


    public Goal(final int id, final int userId, final String title, final String content,
            final LocalDate dueDate) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.dueDate = dueDate;
    // TODO:add column completed_at(completed flag)
    }

    public Goal() {}

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

    public void setUserId(int id) {
        this.userId = id;
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
