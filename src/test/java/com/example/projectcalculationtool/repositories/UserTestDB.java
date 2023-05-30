package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class UserTestDB {

    public void userTestDB() {
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
                    ");");

            statement.addBatch(
                    "INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES" +
                            " ('Thomas', 'Løvkilde', 'thomløv@alpha.com', '123', '1')," +
                            " ('Jesper', 'Nielsen', 'sycko@alpha.com', '123', '1')," +
                            " ('Jesper', 'Zamora', 'jesper@alpha.com', '123', '2')," +
                            " ('Andreas', 'Hjordt', 'sycko1@alpha.com', '123', '2')," +
                            " ('Test', 'test', 'test@alpha.com', '1', '2');"
            );

            statement.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
