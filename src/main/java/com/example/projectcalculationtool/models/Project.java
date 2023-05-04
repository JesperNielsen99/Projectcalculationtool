package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Project {
    private int projectID;
    private int ManagerID;
    private String name;
    private int duration;
    private LocalDate deadline;
    private boolean completed;

    public Project() {
    }

    public Project(int projectID, int managerID, String name, int duration, LocalDate deadline, boolean completed) {
        this.projectID = projectID;
        this.ManagerID = managerID;
        this.name = name;
        this.duration = duration;
        this.deadline = deadline;
        this.completed = completed;
    }


    public int getProjectID() {
        return projectID;
    }

    public int getManagerID() {
        return ManagerID;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public void setManagerID(int managerID) {
        ManagerID = managerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
