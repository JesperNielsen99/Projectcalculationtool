package com.example.projectcalculationtool.repositories.util;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class DB_Connector {
    private static String URL = "jdbc:mysql://localhost:3306/projectcalculationtool_db";
    private static String USER = "root";
    private static String PASS = "root";

    private static Connection connection;

    /*@Value("${spring.datasource.url}")
    public void setUrl(String url) {
        URL = url;
    }

    @Value("${spring.datasource.username}")
    public void setUser(String user) {
        USER = user;
    }

    @Value("${spring.datasource.password}")
    public void setPass(String pass) {
        PASS = pass;
    }*/

    public static Connection getConnection() {
        try {
            if (connection == null) connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}