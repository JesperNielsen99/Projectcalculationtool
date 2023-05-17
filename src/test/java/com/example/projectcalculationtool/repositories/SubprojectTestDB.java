package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ActiveProfiles("test")
public class SubprojectTestDB {

    public void subprojectTestDB(){
        try {
            Connection connection = DB_Connector.getConnection();

            Statement statement = connection.createStatement();

            connection.setAutoCommit(false);

            statement.addBatch("SET foreign_key_checks = 0;");
            statement.addBatch("DROP TABLE IF EXISTS subproject;");

            statement.addBatch("CREATE TABLE subproject\n" +
                    "(\n" +
                    "    subproject_id        INTEGER      NOT NULL AUTO_INCREMENT,\n" +
                    "    project_id           INT          NOT NULL,\n" +
                    "    subproject_name      VARCHAR(255) NOT NULL,\n" +
                    "    subproject_priority  INT          NOT NULL,\n" +
                    "    subproject_duration  INT,\n" +
                    "    subproject_deadline  DATE         NOT NULL,\n" +
                    "    subproject_completed TINYINT      NOT NULL,\n" +
                    "    PRIMARY KEY (subproject_id),\n" +
                    "    FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");");

            statement.executeBatch();
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




