package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository()
public class SubprojectRepository implements ISubprojectRepository {
    String sql = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void createSubproject(Subproject subproject){
        try{
            sql = "INSERT INTO subproject\n" +
                    "(project_id, subproject_name, subproject_priority, subproject_deadline, subproject_duration, subproject_completed\n)"+
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
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




}
