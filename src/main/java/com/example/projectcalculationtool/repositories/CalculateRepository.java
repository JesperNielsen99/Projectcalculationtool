package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CalculateRepository {

    public List<String> getUsers() {
        List<String> users = new ArrayList<>();
        try {
            Connection conn = DB_Connector.getConnection();
            String SQL = "SELECT * FROM users;";
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                users.add(rs.getString("user_name"));
            }
            stmt.close();

            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
