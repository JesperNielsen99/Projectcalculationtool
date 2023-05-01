package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;

public class TaskService {
    ITaskRepository iTaskRepository;

    public TaskService(ITaskRepository iTaskRepository) {
        this.iTaskRepository = iTaskRepository;
    }

    public void createTask(Task task){
        iTaskRepository.createTask(task);
    }
}
