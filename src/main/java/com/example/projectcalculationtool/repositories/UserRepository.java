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
    @Override
    public void createUser(User user) {
        try {
            Connection connection = DB_Connector.getConnection();

            String SQL = "INSERT INTO user (user_first_name, user_last_name, user_email, user_password, user_role_id) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
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
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT * FROM role";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
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
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT role_name FROM role WHERE role_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, roleID);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            Connection connection = DB_Connector.getConnection();
            String SQL = "UPDATE user SET user_first_name = ?, user_last_name = ?, user_password = ?, " +
                    "user_role_id = ? WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
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
            Connection connection = DB_Connector.getConnection();
            String SQL = "DELETE FROM user WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String email, String password) {
        try {
            Connection connection = DB_Connector.getConnection();
            String SQL = "SELECT * FROM user WHERE user_email = ? AND user_password = ?";
            PreparedStatement preparedStatementUserID = connection.prepareStatement(SQL);
            preparedStatementUserID.setString(1, email);
            preparedStatementUserID.setString(2, password);
            ResultSet resultSet = preparedStatementUserID.executeQuery();
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
