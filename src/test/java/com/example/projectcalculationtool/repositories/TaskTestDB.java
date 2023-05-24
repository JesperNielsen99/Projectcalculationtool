package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@ActiveProfiles("test")
@Component
public class TaskTestDB {

    public void taskTestDB(){
        try{
            Connection conn = DB_Connector.getConnection();

            Statement statement = conn.createStatement();

            conn.setAutoCommit(false);

            statement.addBatch("SET foreign_key_checks = 0;");
            statement.addBatch("DROP TABLE IF EXISTS task");

            statement.addBatch("CREATE TABLE task (\n" +
                    "\ttask_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "    subproject_id INT NOT NULL,\n" +
                    "    task_name VARCHAR(50) NOT NULL,\n" +
                    "    task_description VARCHAR(255) NOT NULL,\n" +
                    "    task_priority INT NOT NULL CHECK (task_priority >= 1 AND task_priority <= 5),\n" +
                    "    task_duration INT NOT NULL CHECK (task_duration >= 0),\n" +
                    "    task_deadline DATE NOT NULL CHECK (task_deadLine <= \"3000-12-31\"),\n" +
                    "    task_completed TINYINT NOT NULL,\n" +
                    "    task_manager_name VARCHAR(50) NOT NULL,\n" +
                    "    PRIMARY KEY (task_id),\n" +
                    "    FOREIGN KEY (subproject_id) REFERENCES subproject (subproject_id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                    ");");

            statement.executeBatch();
            conn.commit();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
