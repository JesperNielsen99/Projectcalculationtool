package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Project> getProjects(int managerID) {
        List<Project> projectList = new ArrayList<>();

        try {
            Connection connection = DB_Connector.getConnection();

            String SQL = "SELECT * FROM project WHERE project_manager_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1,managerID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                projectList.add(new Project(
                        resultSet.getInt("project_id"),
                        resultSet.getInt("project_manager_id"),
                        resultSet.getString("project_name"),
                        resultSet.getInt("project_duration"),
                        LocalDate.parse(resultSet.getString("project_deadline")),
                        resultSet.getBoolean("project_completed")
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return projectList;
    }

}


