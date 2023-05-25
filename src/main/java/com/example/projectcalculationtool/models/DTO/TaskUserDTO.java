package com.example.projectcalculationtool.models.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskUserDTO {
    private int taskID;
    private int subprojectID;
    private String name;
    private String description;
    private int priority;
    private int duration;
    private LocalDate deadline;
    private boolean completed;
    private String managerName;

    private List<String> assignedUsers = new ArrayList<>();

    public TaskUserDTO(){}

    public TaskUserDTO(int taskID, int subprojectID, String name, String description, int priority,
                int duration, LocalDate deadline, Boolean completed, String managerName, List<String> assignedUsers) {
        this.taskID = taskID;
        this.subprojectID = subprojectID;
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.duration = duration;
        this.deadline = deadline;
        this.completed = completed;
        this.managerName = managerName;
        this.assignedUsers = assignedUsers;
    }

    public void addAssignedUser(String user){
        assignedUsers.add(user);
    }

    public List<String> getAssignedUsers() {
        return assignedUsers;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }
}
