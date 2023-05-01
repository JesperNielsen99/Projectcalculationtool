package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Task {
    private int taskID;
    private int subprojectID;
    private String name;
    private int priority;
    private LocalDate deadline;
    private int duration;
    private Boolean completed;

    public Task(){}

    public Task(int taskID, int subprojectID, String name, int priority, LocalDate deadline, int duration, Boolean completed) {
        this.taskID = taskID;
        this.subprojectID = subprojectID;
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.duration = duration;
        this.completed = completed;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
