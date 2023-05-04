package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Subproject {
    private int subprojectID;
    private int projectID;
    private String name;
    private int priority;
    private int duration;
    private LocalDate deadline;
    private Boolean completed;

    public Subproject(int subprojectID, int projectID, String name, int priority, int duration, LocalDate deadline, Boolean completed) {
        this.subprojectID = subprojectID;
        this.projectID = projectID;
        this.name = name;
        this.priority = priority;
        this.duration = duration;
        this.deadline = deadline;
        this.completed = completed;
    }

    public Subproject() {
    }

    public int getSubprojectID() {
        return subprojectID;
    }

    public void setSubprojectID(int subprojectID) {
        this.subprojectID = subprojectID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
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
