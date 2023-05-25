package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.DTO.TaskUserDTO;
import com.example.projectcalculationtool.models.DTO.UserAssignDTO;
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
            String SQL = "SELECT * FROM task WHERE subproject_id = ?;";

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

    @Override
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

    @Override
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
                unassignedUsers.add(user);
            }
            return unassignedUsers;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    @Override
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

    @Override
    public List<Task> getUserTasks(int userID) {
        try {
            Connection conn = DB_Connector.getConnection();
            String sql = "SELECT * FROM task JOIN task_user USING (task_id) WHERE user_id = ?";
            PreparedStatement preparedstatement = conn.prepareStatement(sql);
            preparedstatement.setInt(1, userID);
            ResultSet resultSet = preparedstatement.executeQuery();
            List<Task> tasks = new ArrayList<>();
            while (resultSet.next()) {
                tasks.add(new Task(resultSet.getInt("task_id"),
                        resultSet.getInt("subproject_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getInt("task_priority"),
                        resultSet.getInt("task_duration"),
                        LocalDate.parse(resultSet.getString("task_deadline")),
                        resultSet.getBoolean("task_completed"),
                        resultSet.getString("task_manager_name")
                    )
                );
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* ------------------------------------ New Assign & Unassigned ----------------------------------------- */

    // Getting a list of users that can be assigned
    @Override
    public List<UserAssignDTO> getUserAssignDTO(){ // Change name to getUserUnassignedDTO
        List<UserAssignDTO> userAssignDTOList = new ArrayList<>(); // Find a better name for list
        try {
            Connection connection = DB_Connector.getConnection();

            String SQL = "SELECT user_id, user_first_name, user_last_name FROM user;";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL);
            while(resultSet.next()){
                userAssignDTOList.add(new UserAssignDTO(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_first_name") + " " + resultSet.getString("user_last_name")

                ));
            }

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

        return userAssignDTOList;
    }

    @Override
    public List<TaskUserDTO> getTaskUsersDTO(int subprojectID){
        List<TaskUserDTO> taskUserList = new ArrayList<>();
        //TaskUserDTO taskUserDTO = null;

        try {
            Connection conn = DB_Connector.getConnection();
            String SQL =
                    "SELECT task.task_id, subproject_id, task_name, task_description, task_priority, task_duration, " +
                            "task_deadline, task_completed, task_manager_name, user.user_id, user_first_name, user_last_name " +
                            "FROM task JOIN user JOIN task_user ON task.task_id = task_user.task_id " +
                            "AND user.user_id = task_user.user_id AND subproject_id = ?;";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, subprojectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            int currentTaskID = 0;
            while (resultSet.next()) {
                int taskID = resultSet.getInt("task_id");

                if(currentTaskID == taskID) {
                    String user = resultSet.getString("user_first_name")  + " " + resultSet.getString("user_last_name");

                    int lastTask = taskUserList.size();
                    TaskUserDTO tUserDTO = taskUserList.get(lastTask-1);
                    tUserDTO.addAssignedUser(user);

                } else {
                    taskUserList.add(new TaskUserDTO(
                            taskID,
                            resultSet.getInt("subproject_id"),
                            resultSet.getString("task_name"),
                            resultSet.getString("task_description"),
                            resultSet.getInt("task_priority"),
                            resultSet.getInt("task_duration"),
                            LocalDate.parse(resultSet.getString("task_deadline")),
                            resultSet.getBoolean("task_completed"),
                            resultSet.getString("task_manager_name"),
                            new ArrayList<>(List.of(resultSet.getString("user_first_name") + " " + resultSet.getString("user_last_name")))));

                    currentTaskID = taskID;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return taskUserList;
    }

    @Override
    public void addAssignedUsersToTask(List<Integer> userID, int taskID){
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "INSERT INTO task_user VALUES (?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            for(int UID : userID) {
                preparedStatement.setInt(UID,taskID);
            }

        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }

    }
}
