package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubprojectRepository implements ISubprojectRepository {

    @Override
    public void createSubproject(Subproject subproject) {
        try {
            Connection connection = DB_Connector.getConnection();
            String sql = "INSERT INTO subproject\n" +
                    "(project_id, \n" +
                    "subproject_name, \n" +
                    "subproject_priority, \n" +
                    "subproject_duration, \n" +
                    "subproject_deadline, \n" +
                    "subproject_completed)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, subproject.getProjectID());
            preparedStatement.setString(2, subproject.getName());
            preparedStatement.setInt(3, subproject.getPriority());
            preparedStatement.setInt(4, subproject.getDuration());
            preparedStatement.setDate(5, Date.valueOf(subproject.getDeadline()));
            preparedStatement.setBoolean(6, subproject.isCompleted());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subproject> getSubprojects(int projectID) {
        List<Subproject> subprojectList = new ArrayList<>();
        try {
            Connection connection = DB_Connector.getConnection();
            String sql = "SELECT * FROM subproject WHERE project_id = ?\n" +
                    "ORDER BY subproject_completed, subproject_priority";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subprojectList.add(new Subproject(
                        resultSet.getInt("subproject_id"),
                        projectID,
                        resultSet.getString("subproject_name"),
                        resultSet.getInt("subproject_priority"),
                        LocalDate.parse(resultSet.getString("subproject_deadline")),
                        resultSet.getInt("subproject_duration"),
                        resultSet.getBoolean("subproject_completed")
                ));
            }

            return subprojectList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subproject getSubproject(int subprojectID) {
        Subproject subproject = null;
        try {
            Connection connection = DB_Connector.getConnection();
            String sql = "SELECT * FROM subproject WHERE subproject_id= ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, subprojectID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                subproject = new Subproject(
                        resultSet.getInt("subproject_id"),
                        resultSet.getInt("project_id"),
                        resultSet.getString("subproject_name"),
                        resultSet.getInt("subproject_priority"),
                        LocalDate.parse(resultSet.getString("subproject_deadline")),
                        resultSet.getInt("subproject_duration"),
                        resultSet.getBoolean("subproject_completed")
                );
            }

            return subproject;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSubproject(Subproject subproject) {
        try {
            Connection connection = DB_Connector.getConnection();
            String sql = "UPDATE subproject \n" +
                    "SET subproject_name = ?,\n" +
                    "subproject_priority = ?, \n" +
                    "subproject_duration = ?, \n" +
                    "subproject_deadline = ?, \n" +
                    "subproject_completed = ? \n" +
                    "WHERE subproject_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, subproject.getName());
            preparedStatement.setInt(2, subproject.getPriority());
            preparedStatement.setInt(3, subproject.getDuration());
            preparedStatement.setDate(4, Date.valueOf(subproject.getDeadline()));
            preparedStatement.setBoolean(5, subproject.isCompleted());
            preparedStatement.setInt(6, subproject.getSubprojectID());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteSubproject(int subprojectID) {
        try {
            Connection connection = DB_Connector.getConnection();
            String sql = "DELETE FROM subproject WHERE subproject_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, subprojectID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSubprojectDuration(int subprojectID) {
        try {
            Connection connection = DB_Connector.getConnection();
            String sql = "SELECT task_duration FROM task WHERE subproject_id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, subprojectID);

            ResultSet resultSet = preparedStatement.executeQuery();
            int duration = 0;
            while (resultSet.next()) {
                duration += resultSet.getInt("task_duration");
            }

            String sql1 = "UPDATE subproject SET subproject_duration = ? WHERE subproject_id = ?";

            preparedStatement = connection.prepareStatement(sql1);

            preparedStatement.setInt(1, duration);
            preparedStatement.setInt(2, subprojectID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
