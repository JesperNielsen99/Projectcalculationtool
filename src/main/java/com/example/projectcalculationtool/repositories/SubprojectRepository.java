package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository()
public class SubprojectRepository implements ISubprojectRepository {
    @Override
    public void createSubproject(Subproject subproject){
        try{
            Connection connection = DB_Connector.getConnection();
            String SQL = "INSERT INTO subproject\n" +
                    "(project_id, subproject_name, subproject_priority, subproject_deadline, subproject_duration, subproject_completed\n)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, subproject.getProjectID());
            preparedStatement.setString(2, subproject.getName());
            preparedStatement.setInt(3, subproject.getPriority());
            preparedStatement.setDate(4, Date.valueOf(subproject.getDeadline()));
            preparedStatement.setInt(1, subproject.getDuration());
            preparedStatement.setBoolean(1, false);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subproject> getSubprojects(int projectID) {
        List<Subproject> subprojectList = new ArrayList<>();

        try {
            Connection connection = DB_Connector.getConnection();

            String SQL = "SELECT * FROM subproject WHERE project_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1,projectID); // change to session

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                subprojectList.add(new Subproject(
                        resultSet.getInt("subproject_id"),
                        projectID,
                        resultSet.getString("subproject_name"),
                        resultSet.getInt("subproject_priority"),
                        resultSet.getInt("subproject_duration"),
                        LocalDate.parse(resultSet.getString("subproject_deadline")),
                        resultSet.getBoolean("subproject_completed")
                ));
            }
            return subprojectList;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
