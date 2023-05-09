package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ActiveProfiles("test")
public class ProjectTestDB {

    public void projectTestDB(){
        try {
            Connection connection = DB_Connector.getConnection();

            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);

            statement.addBatch("SET foreign_key_checks = 0;");
            statement.addBatch("DROP TABLE IF EXISTS project;");

            statement.addBatch("CREATE TABLE project (\n" +
                    "\tproject_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "project_manager_id INT NOT NULL,\n" +
                    "project_name VARCHAR(255) NOT NULL,\n" +
                    "project_duration INT,\n" +
                    "project_deadline DATE NOT NULL,\n" +
                    "project_completed TINYINT NOT NULL,\n" +
                    "PRIMARY KEY (project_id),\n" +
                    "FOREIGN KEY (project_manager_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
            ");");

            statement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
