package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Task;
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
            String SQL = "INSERT INTO task (subproject_id, task_name, task_description, task_priority, task_duration, task_deadline, task_completed) VALUES (?,?,?,?,?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,task.getSubprojectID());
            preparedStatement.setString(2, task.getName());
            preparedStatement.setString(3, task.getDescription());
            preparedStatement.setInt(4,task.getPriority());
            preparedStatement.setInt(5,task.getDuration());
            preparedStatement.setDate(6, Date.valueOf(task.getDeadline()));
            preparedStatement.setBoolean(7, task.getIsCompleted());

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
            String SQL = "SELETC * FROM task WHERE subproject_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1,subprojectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                tasks.add(new Task(
                        resultSet.getInt("task_id"),
                        resultSet.getInt("subproject_id"),
                        resultSet.getString("task_name"),
                        resultSet.getString("task_description"),
                        resultSet.getInt("task_priority"),
                        resultSet.getInt("task_duration"),
                        LocalDate.parse(resultSet.getString("task_deadline")),
                        resultSet.getBoolean("task_completed")
                ));
            }

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public void updateTask(Task task) {

    }

}
