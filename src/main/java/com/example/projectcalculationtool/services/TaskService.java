package com.example.projectcalculationtool.services;

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

    public List<Task> getTasks(int subprojectID){
        return taskRepository.getTasks(subprojectID);
    }

    public void updateTask(Task task) {
        taskRepository.updateTask(task);
    }
}
