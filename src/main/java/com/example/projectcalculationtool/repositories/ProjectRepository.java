package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepo;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class ProjectRepository implements IProjectRepo {


    @Override
    public void createProject(Project project) {
        try{
            Connection connection = DB_Connector.getConnection();

            String SQL = "INSERT INTO x (x) VALUES (?,?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, project.getName());
            preparedStatement.setDate(2, Date.valueOf(project.getDeadline()));
            preparedStatement.setInt(3, project.getDuration());
            preparedStatement.setBoolean(4, project.isCompleted());
            preparedStatement.setInt(5, project.getManagerID());


            preparedStatement.executeUpdate();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }



    }
}
