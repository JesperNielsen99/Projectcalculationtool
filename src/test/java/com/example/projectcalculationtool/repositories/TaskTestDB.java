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

    public void taskUserTestDB() {
        try{
            Connection conn = DB_Connector.getConnection();
            Statement statement = conn.createStatement();

            conn.setAutoCommit(false);

            statement.addBatch("SET foreign_key_checks = 0;");
            statement.addBatch("DROP TABLE IF EXISTS user");
            statement.addBatch("CREATE TABLE user (\n" +
                    "\tuser_id         INTEGER      NOT NULL AUTO_INCREMENT,\n" +
                    "\tuser_first_name VARCHAR(255) NOT NULL,\n" +
                    "\tuser_last_name  VARCHAR(255) NOT NULL,\n" +
                    "\tuser_email      VARCHAR(255) NOT NULL,\n" +
                    "\tuser_password   VARCHAR(255) NOT NULL,\n" +
                    "\tuser_role_id    INT          NOT NULL,\n" +
                    "\tPRIMARY KEY (user_id),\n" +
                    "\tFOREIGN KEY (user_role_id) REFERENCES role (role_id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "\tUNIQUE INDEX (user_email),\n" +
                    "\tCONSTRAINT check_user_email CHECK (user_email LIKE \"%alpha.com\")\n" +
                    ");"
            );

            statement.addBatch(
                    "INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES" +
                            " ('Thomas', 'Løvkilde', 'thomløv@alpha.com', '123', '1')," +
                            " ('Jesper', 'Nielsen', 'sycko@alpha.com', '123', '1')," +
                            " ('Jesper', 'Zamora', 'jesper@alpha.com', '123', '2')," +
                            " ('Andreas', 'Hjordt', 'sycko1@alpha.com', '123', '2');"
            );

            statement.addBatch("DROP TABLE IF EXISTS task_user");
            statement.addBatch("CREATE TABLE task_user (\n" +
                    "\ttask_id INT NOT NULL,\n" +
                    "\tuser_id INT NOT NULL,\n" +
                    "    FOREIGN KEY (task_id) REFERENCES task (task_id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "    FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE ON UPDATE CASCADE,\n" +
                    "    PRIMARY KEY (task_id, user_id)\n" +
                    ");"
            );

            statement.addBatch("INSERT INTO task_user (task_id, user_id) VALUES\n" +
                    "\t(1,1),\n" +
                    "    (1,2),\n" +
                    "    (2,1),\n" +
                    "    (2,3),\n" +
                    "    (2,4);"
            );

            statement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
