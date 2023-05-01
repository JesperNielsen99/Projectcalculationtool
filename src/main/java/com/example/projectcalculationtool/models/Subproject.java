package com.example.projectcalculationtool.models;

import java.time.LocalDate;

public class Subproject {
    private int subprojectID;
    private int projectID;
    private String name;
    private int priority;
    private LocalDate deadLine;
    private int duration;
    private Boolean completed;

    public Subproject(int subprojectID, int projectID, String name, int priority, LocalDate deadLine, int duration, Boolean completed) {
        this.subprojectID = subprojectID;
        this.projectID = projectID;
        this.name = name;
        this.priority = priority;
        this.deadLine = deadLine;
        this.duration = duration;
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

    public LocalDate getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDate deadLine) {
        this.deadLine = deadLine;
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
