package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Task {
    private int taskID;
    private int subprojectID;
    private String name;
    private String description;
    private int priority;
    private int duration;
    private LocalDate deadline;
    private Boolean isCompleted;

    public Task(){}

    public Task(int taskID, int subprojectID, String name, String description, int priority, int duration, LocalDate deadline, Boolean isCompleted) {
        this.taskID = taskID;
        this.subprojectID = subprojectID;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.duration = duration;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getSubprojectID() {
        return subprojectID;
    }

    public void setSubprojectID(int subprojectID) {
        this.subprojectID = subprojectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
