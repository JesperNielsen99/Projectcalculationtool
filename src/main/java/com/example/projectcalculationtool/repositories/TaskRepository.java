package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.interfaces.ITaskRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskRepository implements ITaskRepository {

    @Override
    public void createTask(Task task) {
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "INSERT INTO x (x,x,x,x,x) VALUES (?,?,?,?,?);";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setString(1, task.getName());
            preparedStatement.setDate(2, Date.valueOf(task.getDeadline()));
            preparedStatement.setInt(3, task.getDuration());
            preparedStatement.setBoolean(4, task.getCompleted());
            preparedStatement.setInt(5, task.getSubprojectID());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Task> getTasks(int subprojectID) {
        return null;
    }
}
