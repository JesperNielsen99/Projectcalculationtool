package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.TaskRepository;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import com.example.projectcalculationtool.services.comparators.CompletedComparator;
import com.example.projectcalculationtool.services.comparators.PriorityComparator;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaskService {
    private ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        taskRepository.createTask(task);
    }

    public List<Task> getTasks(int subprojectID){
        List<Task> tasks = taskRepository.getTasks(subprojectID);
        Collections.sort(tasks, new CompletedComparator().thenComparing(new PriorityComparator()));
        return tasks;
    }

    public Task getTask(int taskID){
        return taskRepository.getTask(taskID);
    }

    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    public void deleteTask(int taskID){
        taskRepository.deleteTask(taskID);
    }

    public List<User> getUsersAssignedTo(int taskID){
        return taskRepository.getUsersAssignedTo(taskID);
    }

    public List<User> getUsersUnassignedTo(int taskID) {
        return taskRepository.getUsersUnassignedTo(taskID);
    }

    public void addUsersToTask(List<User> users, int taskID){
        taskRepository.addUsersToTask(users, taskID);
    }
}
