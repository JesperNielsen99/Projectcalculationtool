package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("User_DB")
public class UserRepository implements IUserRepository {
    String SQL = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    @Override
    public void createUser(User user) {
        try {
            SQL = "INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRoleID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Role> getRoles() {
        try {
            SQL = "SELECT * FROM role";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL);
            List<Role> roles = new ArrayList<>();
            while (resultSet.next()) {
                roles.add(new Role(resultSet.getInt("role_id"), resultSet.getString("role_name")));
            }
            return roles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getRole(int roleID) {
        try {
            SQL = "SELECT role_name FROM role WHERE role_id = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, roleID);
            resultSet = preparedStatement.executeQuery();
            String role = "";
            if (resultSet.next()) {
                role = resultSet.getString("role_name");
            }
            return role;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void updateUser(User user) {
        try {
            SQL = "UPDATE user SET user_first_name = ?, user_last_name = ?, user_password = ?, " +
                    "user_role_id = ? WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getRoleID());
            preparedStatement.setInt(5, user.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteUser(int userID) {
        try {
            SQL = "DELETE FROM user WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String email, String password) {
        try {
            SQL = "SELECT * FROM user WHERE user_email = ? AND user_password = ?";
            PreparedStatement preparedStatementUserID = connection.prepareStatement(SQL);
            preparedStatementUserID.setString(1, email);
            preparedStatementUserID.setString(2, password);
            resultSet = preparedStatementUserID.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("user_first_name"),
                        resultSet.getString("user_last_name"),
                        resultSet.getString("user_email"),
                        resultSet.getString("user_password"),
                        resultSet.getInt("user_role_id")
                        );
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
