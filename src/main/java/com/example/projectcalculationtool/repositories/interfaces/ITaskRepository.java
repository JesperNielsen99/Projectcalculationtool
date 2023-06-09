package com.example.projectcalculationtool.repositories.interfaces;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;

import java.util.List;

public interface ITaskRepository {

    void createTask(Task task);

    List<Task> getTasks(int subprojectID);

    Task getTask(int taskID);

    void updateTask(Task task);

    void deleteTask(int taskID);

    List<User> getUsersUnassignedTo(int taskID);

    List<User> getUsersAssignedTo(int taskID);

    List<TaskUserDTO> getUserTasks(int userID);

    /* ------------------------------------ New Assign & Unassigned ----------------------------------------- */

    List<TaskUserDTO> getTaskUsersDTO(int subprojectID);

    void addAssignedUsersToTask(List<Integer> userIDs, int taskID);

    void removeAssignedUsersFromTask(List<Integer> userIDs, int taskID);
}
