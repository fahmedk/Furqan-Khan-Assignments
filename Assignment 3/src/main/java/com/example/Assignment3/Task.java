package com.example.Assignment3;

public class Task {
    private Long id;
    private String description;
    private Boolean completed;

    // Constructors
    public Task() {}

    public Task(Long id, String description, Boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getCompleted() { return completed; }
    public void setCompleted(Boolean completed) { this.completed = completed; }
}

