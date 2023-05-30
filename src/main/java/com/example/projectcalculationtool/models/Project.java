package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Project {
    private int projectID;
    private int managerID;
    private String name;
    private int duration;
    private LocalDate deadline;
    private boolean completed;

    public Project() {
    }

    public Project(int projectID, int managerID, String name, int duration, LocalDate deadline, boolean completed) {
        this.projectID = projectID;
        this.managerID = managerID;
        this.name = name;
        this.duration = duration;
        this.deadline = deadline;
        this.completed = completed;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
