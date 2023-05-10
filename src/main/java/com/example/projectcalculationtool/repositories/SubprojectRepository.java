package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Subproject;
import com.example.projectcalculationtool.repositories.interfaces.ISubprojectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;

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
}
