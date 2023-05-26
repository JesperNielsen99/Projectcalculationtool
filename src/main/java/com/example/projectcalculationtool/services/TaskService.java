package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;
import com.example.projectcalculationtool.services.comparators.TaskUserDTOCompletedComparator;
import com.example.projectcalculationtool.services.comparators.TaskUserDTOPriorityComparator;
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

    public List<TaskUserDTO> getUserTasks(int userID) {
        List<TaskUserDTO> taskUserDTOs = taskRepository.getUserTasks(userID);
        taskUserDTOs.sort(new TaskUserDTOCompletedComparator().thenComparing(new TaskUserDTOPriorityComparator()));
        return taskUserDTOs ;
    }

    /* ------------------------------------ New Assign & Unassigned ----------------------------------------- */


    // List of tasks with a list of users that have been added to the task
    public List<TaskUserDTO> getTaskUsersDTO(int subprojectID) {
        List<TaskUserDTO> taskUserDTOs = taskRepository.getTaskUsersDTO(subprojectID);
        taskUserDTOs.sort(new TaskUserDTOCompletedComparator().thenComparing(new TaskUserDTOPriorityComparator()));
        return taskUserDTOs ;
    }

    // List of user-ids that needs to be added to table task_user in database
    public void addAssignedUsersToTask(List<Integer> userIDs, int taskID) {
       taskRepository.addAssignedUsersToTask(userIDs, taskID);
    }

    public void removeAssignedUsersFromTask(List<Integer> userIDs, int taskID) {
        taskRepository.removeAssignedUsersFromTask(userIDs,taskID);
    }

}
