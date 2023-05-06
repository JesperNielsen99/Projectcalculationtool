package com.example.projectcalculationtool.repositories.interfaces;

import com.example.projectcalculationtool.models.Task;

import java.util.List;

public interface ITaskRepository {

    void createTask(Task task);

    List<Task> getTasks(int subprojectID);

    void updateTask(Task task);
}
