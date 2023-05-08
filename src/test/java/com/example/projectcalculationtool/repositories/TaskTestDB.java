package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Task;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
public class TaskTestDB {
    @Autowired
    private DB_Connector db_connector;
    private Connection conn;
    @BeforeEach
    void setUp(){
        db_connector.setUrl("jdbc:mysql://localhost:3306/tasktest_db");
        db_connector.setUser("root");
        db_connector.setPass("Jw-180490");
        conn = DB_Connector.getConnection();
    }

    @Test
    public void taskTestDB(){
        try{
            Statement statement = conn.createStatement();

            conn.setAutoCommit(false);

            statement.addBatch("SET foreign_key_checks = 0;");

            statement.addBatch("DROP TABLE IF EXISTS task");

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
