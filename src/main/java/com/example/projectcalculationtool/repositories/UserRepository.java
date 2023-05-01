package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository("User_DB")
public class UserRepository implements IUserRepository {
    String SQL = null;
    Connection connection = DB_Connector.getConnection();
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    @Override
    public void createUser(User user) {
        try {
            SQL = "INSERT INTO user (user_first_name, user_last_name, user_mail, user_password, user_role) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setInt(5, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    @Override
    public User updateUser(User user) {
        try {
            SQL = "UPDATE user SET UserName = ?, Email = ?, Password = ? WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getUserID());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User DeleteUser(User user) {
        try {
            SQL = "SELECT WishlistID FROM wishlist WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.executeQuery();
            while (resultSet.next()) {
                int wishlistID = resultSet.getInt("WishlistID");
                SQL = "DELETE FROM wishlist_wish WHERE wishlistID = ?";
                preparedStatement.setInt(1, wishlistID);
                preparedStatement.executeUpdate();
            }
            SQL = "DELETE FROM wishlist WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.executeUpdate();
            SQL = "DELETE FROM user WHERE UserID = ?";
            preparedStatement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, user.getUserID());
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUser(String email, String password) {
        try {
            SQL = "SELECT * FROM user WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatementUserID = connection.prepareStatement(SQL);
            preparedStatementUserID.setString(1, email);
            preparedStatementUserID.setString(2, password);
            resultSet = preparedStatementUserID.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User( resultSet.getInt("UserID"), resultSet.getString("UserName"), resultSet.getString("Email"), resultSet.getString("Password"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
}