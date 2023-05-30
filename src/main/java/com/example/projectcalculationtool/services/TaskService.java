package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;
import org.springframework.stereotype.Service;

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

    public Task getTask(int taskID) {
        return taskRepository.getTask(taskID);
    }

    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }

    public void deleteTask(int taskID) {
        taskRepository.deleteTask(taskID);
    }

    public List<TaskUserDTO> getUserTasks(int userID) {
        return taskRepository.getUserTasks(userID);
    }

    /* ------------------------------------ New Assign & Unassigned ----------------------------------------- */

    public List<TaskUserDTO> getTaskUsersDTO(int subprojectID) {
        return taskRepository.getTaskUsersDTO(subprojectID);
    }

    public void addAssignedUsersToTask(List<Integer> userIDs, int taskID) {
        taskRepository.addAssignedUsersToTask(userIDs, taskID);
    }

    public void removeAssignedUsersFromTask(List<Integer> userIDs, int taskID) {
        taskRepository.removeAssignedUsersFromTask(userIDs, taskID);
    }

}
