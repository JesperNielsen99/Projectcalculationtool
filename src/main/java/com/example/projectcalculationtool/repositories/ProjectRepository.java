package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Project;
import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.interfaces.IProjectRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository implements IProjectRepository {

    /* ------------------------------------ Create project ----------------------------------------- */

    @Override
    public void createProject(Project project) {
        try{
            Connection connection = DB_Connector.getConnection();

            String SQL = "INSERT INTO project (project_manager_id, project_name, project_duration, " +
                    "project_deadline, project_completed) VALUES (?,?,?,?,?)";

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

    /* ------------------------------------ Get list<project> ----------------------------------------- */
    @Override
    public List<Project> getProjects(int managerID) {
        List<Project> projectList = new ArrayList<>();

        try {
            Connection connection = DB_Connector.getConnection();

            String SQL = "SELECT * FROM project WHERE project_manager_id = ?\n" +
                    "ORDER BY project_completed";
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

    /* ------------------------------------ Get project (single) ----------------------------------------- */

    @Override
    public Project getProject(int projectID) {
        Project project = null;
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "SELECT * FROM project WHERE project_id= ?";

            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, projectID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                project = new Project(
                        resultSet.getInt("project_id"),
                        resultSet.getInt("project_manager_id"),
                        resultSet.getString("project_name"),
                        resultSet.getInt("project_duration"),
                        LocalDate.parse(resultSet.getString("project_deadline")),
                        resultSet.getBoolean("project_completed")
                );
            }
            return project;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* ------------------------------------ Update project ----------------------------------------- */

    @Override
    public void updateProject(Project project) {
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "UPDATE project SET project_id=?, project_manager_id=?, project_name=?, project_duration=?, project_deadline=?, project_completed=? WHERE project_id=?;";


            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1,project.getProjectID());
            preparedStatement.setInt(2, project.getManagerID());
            preparedStatement.setString(3,project.getName());
            preparedStatement.setInt(4, project.getDuration());
            preparedStatement.setDate(5, Date.valueOf(project.getDeadline()));
            preparedStatement.setBoolean(6, project.isCompleted());
            preparedStatement.setInt(7,project.getProjectID());



            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /* ------------------------------------ Delete project ----------------------------------------- */

    @Override
    public void deleteProject(int projectID) {
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "DElETE FROM project WHERE project_id=?;";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
            preparedStatement.setInt(1, projectID);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProjectDuration(int projectID) {
        try{
            Connection connection = DB_Connector.getConnection();
            String sql = "SELECT subproject_duration FROM subproject WHERE project_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, projectID);
            ResultSet resultSet = preparedStatement.executeQuery();
            int duration = 0;
            while (resultSet.next()) {
                duration += resultSet.getInt("subproject_duration");
            }
            String prepSql = "UPDATE project SET project_duration = ? WHERE project_id = ?";
            preparedStatement = connection.prepareStatement(prepSql);
            preparedStatement.setInt(1, duration);
            preparedStatement.setInt(2, projectID);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}


