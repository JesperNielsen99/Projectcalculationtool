package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.dto.TaskUserDTO;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskRepository implements ITaskRepository {

    @Override
    public void createTask(Task task) {
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "INSERT INTO task (subproject_id, task_name, task_description, task_priority, " +
                    "task_duration, task_deadline, task_completed, task_manager_name) VALUES (?,?,?,?,?,?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, task.getSubprojectID());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setInt(4, task.getPriority());
            preparedStatement.setInt(5, task.getDuration());
            preparedStatement.setDate(6, Date.valueOf(task.getDeadline()));
            preparedStatement.setBoolean(7, task.isCompleted());
            preparedStatement.setString(8, task.getManagerName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Task> getTasks(int subprojectID) {
        List<Task> tasks = new ArrayList<>();
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "SELECT * FROM task WHERE subproject_id = ?\n" +
                    "ORDER BY task_completed, task_priority";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, subprojectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                tasks.add(new Task(
                        resultSet.getInt("task_id"),
                        resultSet.getInt("subproject_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getInt("task_priority"),
                        resultSet.getInt("task_duration"),
                        LocalDate.parse(resultSet.getString("task_deadline")),
                        resultSet.getBoolean("task_completed"),
                        resultSet.getString("task_manager_name")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tasks;
    }

    @Override
    public Task getTask(int taskID) {
        Task task = null;
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "SELECT * FROM task WHERE task_id= ?";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, taskID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                task = new Task(
                        resultSet.getInt("task_id"),
                        resultSet.getInt("subproject_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getInt("task_priority"),
                        resultSet.getInt("task_duration"),
                        LocalDate.parse(resultSet.getString("task_deadline")),
                        resultSet.getBoolean("task_completed"),
                        resultSet.getString("task_manager_name")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return task;
    }

    @Override
    public void updateTask(Task task) {
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "UPDATE task SET task_name=?, task_description=?, task_priority=?, " +
                    "task_duration=?, task_deadline=?, task_completed=?, task_manager_name=? WHERE task_id=?;";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, task.getName());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setInt(3, task.getPriority());
            preparedStatement.setInt(4, task.getDuration());
            preparedStatement.setDate(5, Date.valueOf(task.getDeadline()));
            preparedStatement.setBoolean(6, task.isCompleted());
            preparedStatement.setString(7, task.getManagerName());

            preparedStatement.setInt(8, task.getTaskID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteTask(int taskID) {
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "DElETE FROM task WHERE task_id = ?;";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, taskID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // used
    public List<User> getUsersAssignedTo(int taskID){

        List<User> assignedUsers = new ArrayList<>();

        try{
            Connection conn = DB_Connector.getConnection();
            String sql = "SELECT * FROM user WHERE user_id IN \n" +
                    "(SELECT user_id FROM task_user WHERE task_id = ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_first_name"),
                        resultSet.getString("user_last_name"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getInt("user_role_id")
                );
                assignedUsers.add(user);
            }
            return assignedUsers;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override // used
    public List<User> getUsersUnassignedTo(int taskID){

        List<User> unassignedUsers = new ArrayList<>();

        try{
            Connection conn = DB_Connector.getConnection();
            String sql = "SELECT * FROM user WHERE user_id NOT IN \n" +
                    "(SELECT user_id FROM task_user WHERE task_id = ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, taskID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_first_name"),
                        resultSet.getString("user_last_name"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getInt("user_role_id")
                );
                if(user.getRoleID() == 2){
                    unassignedUsers.add(user);
                }

            }
            return unassignedUsers;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override // Remove
    public void addUsersToTask(List<User> users, int taskID) {
        try {
            Connection conn = DB_Connector.getConnection();
            String sql = "INSERT INTO task_user (task_id, user_id) VALUES (?, ?)";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);

            for (User user : users) {
                preparedstatement.setInt(1, taskID);
                preparedstatement.setInt(2, user.getUserID());
                preparedstatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // Integration test
    public List<TaskUserDTO> getUserTasks(int userID) {
        try {
            Connection conn = DB_Connector.getConnection();
            String sql = "SELECT * FROM task JOIN task_user USING (task_id) WHERE user_id = ?\n" +
                    "ORDER BY task_completed, task_priority";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            preparedstatement.setInt(1, userID);
            ResultSet resultSet = preparedstatement.executeQuery();
            List<TaskUserDTO> taskUserDTOs = new ArrayList<>();
            while (resultSet.next()) {
                Task task = new Task(resultSet.getInt("task_id"),
                        resultSet.getInt("subproject_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getInt("task_priority"),
                        resultSet.getInt("task_duration"),
                        LocalDate.parse(resultSet.getString("task_deadline")),
                        resultSet.getBoolean("task_completed"),
                        resultSet.getString("task_manager_name")
                );
                taskUserDTOs.add(new TaskUserDTO(task, getUsersAssignedTo(task.getTaskID())));
            }
            return taskUserDTOs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* ------------------------------------ New Assign & Unassigned ----------------------------------------- */

    @Override //Integration test
    public List<TaskUserDTO> getTaskUsersDTO(int subprojectID){
        List<TaskUserDTO> taskUserList = new ArrayList<>();
        List<Task> taskList = getTasks(subprojectID);
        for (Task task : taskList) {
            TaskUserDTO taskUserDTO = new TaskUserDTO(
                    task,
                    getUsersAssignedTo(task.getTaskID()),
                    getUsersUnassignedTo(task.getTaskID())
            );
            taskUserList.add(taskUserDTO);
        }
        return taskUserList;
    }

    @Override // Integration test
    public void addAssignedUsersToTask(List<Integer> userIDs, int taskID){
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "INSERT INTO task_user (task_id, user_id) VALUES (?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for(int userID : userIDs) {
                preparedStatement.setInt(1,taskID);
                preparedStatement.setInt(2,userID);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }

    @Override // Integration test
    public void removeAssignedUsersFromTask(List<Integer> userIDs, int taskID){
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "DELETE FROM task_user WHERE task_id=? AND user_id=?";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for(int userID : userIDs) {
                preparedStatement.setInt(1,taskID);
                preparedStatement.setInt(2,userID);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
    }
}
