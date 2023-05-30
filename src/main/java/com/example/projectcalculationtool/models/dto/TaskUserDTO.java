package com.example.projectcalculationtool.models.dto;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;

import java.util.List;

public class TaskUserDTO {
    private Task task;
    private List<User> assignedUsers;
    private List<User> unassignedUsers;


    public TaskUserDTO() {
    }

    public TaskUserDTO(Task task, List<User> assignedUsers, List<User> unassignedUsers) {
        this.task = task;
        this.assignedUsers = assignedUsers;
        this.unassignedUsers = unassignedUsers;
    }

    public TaskUserDTO(Task task, List<User> assignedUsers) {
        this.task = task;
        this.assignedUsers = assignedUsers;
    }


    public void addAssignedUser(User user) {
        assignedUsers.add(user);
    }

    public void addUnassignedUser(User user) {
        unassignedUsers.add(user);
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<User> getAssignedUsers() {
        return assignedUsers;
    }

    public void setAssignedUsers(List<User> assignedUsers) {
        this.assignedUsers = assignedUsers;
    }

    public List<User> getUnassignedUsers() {
        return unassignedUsers;
    }

    public void setUnassignedUsers(List<User> unassignedUsers) {
        this.unassignedUsers = unassignedUsers;
    }
}
