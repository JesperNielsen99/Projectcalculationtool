package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Project {
    private String name;
    private LocalDate deadline;
    private int duration;
    private boolean completed;
    private int projectID;
    private int ManagerID;

    public Project() {
    }

    public Project(String name, LocalDate deadline, int duration, boolean completed, int projectID, int managerID) {
        this.name = name;
        this.deadline = deadline;
        this.duration = duration;
        this.completed = completed;
        this.projectID = projectID;
        ManagerID = managerID;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getProjectID() {
        return projectID;
    }

    public int getManagerID() {
        return ManagerID;
    }

    public void setName(String name) { this.name = name; }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setManagerID(int managerID) {
        ManagerID = managerID;
    }
}
