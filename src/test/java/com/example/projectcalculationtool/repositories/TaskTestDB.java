package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TaskTestDB {

    public void taskTestDB(Connection conn){
        try{
            Statement statement = conn.createStatement();

            conn.setAutoCommit(false);

            statement.addBatch("SET foreign_key_checks = 0;");

            statement.addBatch("DROP TABLE task");

            statement.addBatch("CREATE TABLE task (\n" +
                    "\ttask_id INTEGER NOT NULL AUTO_INCREMENT,\n" +
                    "    subproject_id INT NOT NULL,\n" +
                    "    task_name VARCHAR(255) NOT NULL,\n" +
                    "    task_description VARCHAR(255) NOT NULL,\n" +
                    "    task_priority INT NOT NULL,\n" +
                    "    task_duration INT NOT NULL,\n" +
                    "    task_deadline DATE NOT NULL,\n" +
                    "    task_completed TINYINT NOT NULL,\n" +
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
