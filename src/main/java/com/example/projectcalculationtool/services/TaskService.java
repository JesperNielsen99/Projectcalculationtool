package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.DTO.TaskUserDTO;
import com.example.projectcalculationtool.models.DTO.UserAssignDTO;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;
import com.example.projectcalculationtool.services.comparators.CompletedComparator;
import com.example.projectcalculationtool.services.comparators.PriorityComparator;
import org.springframework.stereotype.Service;

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

    public List<Task> getUserTasks(int userID) {
        return taskRepository.getUserTasks(userID);
    }

    /* ------------------------------------ New Assign & Unassigned ----------------------------------------- */

    // List over users that can be assigned
    public List<UserAssignDTO> getUserAssignDTO(){
        return taskRepository.getUserAssignDTO();
    }

    // List of tasks with a list of users that have been added to the task
    public List<TaskUserDTO> getTaskUsersDTO(int subprojectID) {
        return taskRepository.getTaskUsersDTO(subprojectID);
    }

    // List of user-ids that needs to be added to table task_user in database
    public void addAssignedUsersToTask(List<Integer> userID, int taskID) {
       taskRepository.addAssignedUsersToTask(userID, taskID);
    }

}
