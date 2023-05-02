package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProjectRepository implements IProjectRepository {

    @Override
    public void createProject(Project project) {
        try{
            Connection connection = DB_Connector.getConnection();

            String SQL = "INSERT INTO project (project_manager_id, project_name, project_duration, project_deadline, project_completed) VALUES (?,?,?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, project.getManagerID());
            preparedStatement.setString(2,project.getName());
            preparedStatement.setInt(3, project.getDuration());
            preparedStatement.setDate(4, Date.valueOf(project.getDeadline()));
            preparedStatement.setBoolean(5, project.isCompleted());

            preparedStatement.executeUpdate();


        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Project> getProject() {
        return null;
    }


}


