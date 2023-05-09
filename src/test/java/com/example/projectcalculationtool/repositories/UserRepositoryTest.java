package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import com.example.projectcalculationtool.repositories.util.DB_Connector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
    private IUserRepository repository;

    @BeforeEach
    //@Sql(scripts = {"/SQL/testDatabase/reset-user-test-data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void setUp() {
        repository = new UserRepository();
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

    @Test
    void getUserCorrectly() {
        String email = "thomløv@alpha.com";
        String password = "123";

        User user = repository.getUser(email, password);

        Assertions.assertNotNull(user);
    }

    @Test
    void getUserIncorrectEmail() {
        String email = "thomaløv@alpha.com";
        String password = "123";

        User user = repository.getUser(email, password);

        Assertions.assertNull(user);
    }

    @Test
    void getUserIncorrectPassword() {
        String email = "thomløv@alpha.com";
        String password = "12";

        User user = repository.getUser(email, password);

        Assertions.assertNull(user);
    }

    @Test
    void getUserIncorrect() {
        String email = "thomaløv@alpha.com";
        String password = "12";

        User user = repository.getUser(email, password);

        Assertions.assertNull(user);
    }

    @Test
    void getUserIDCorrect() {
        int expectedUserID = 1;

        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserID = user.getUserID();

        Assertions.assertEquals(expectedUserID, actualUserID);
    }

    @Test
    void getUserIDIncorrect() {
        int expectedUserID = 2;

        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserID = user.getUserID();

        Assertions.assertNotEquals(expectedUserID, actualUserID);
    }

    @Test
    void getUserFirstNameCorrect() {
        String expectedUserFirstName = "Thomas";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserFirstName = user.getFirstName();

        Assertions.assertEquals(expectedUserFirstName, actualUserFirstName);
    }

    @Test
    void getUserFirstNameIncorrect() {
        String expectedUserFirstName = "Tomas";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserFirstName = user.getFirstName();

        Assertions.assertNotEquals(expectedUserFirstName, actualUserFirstName);
    }

    @Test
    void getUserLastNameCorrect() {
        String expectedUserLastName = "Løvkilde";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserLastName = user.getLastName();

        Assertions.assertEquals(expectedUserLastName, actualUserLastName);
    }

    @Test
    void getUserLastNameIncorrect() {
        String expectedUserLastName = "Løvekilde";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserLastName = user.getLastName();

        Assertions.assertNotEquals(expectedUserLastName, actualUserLastName);
    }



    @Test
    void getUserEmailCorrect() {
        String expectedUserEmail = "thomløv@alpha.com";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserEmail = user.getEmail();

        Assertions.assertEquals(expectedUserEmail, actualUserEmail);
    }

    @Test
    void getUserEmailIncorrect() {
        String expectedUserEmail = "thomløve@alpha.com";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserEmail = user.getEmail();

        Assertions.assertNotEquals(expectedUserEmail, actualUserEmail);
    }

    @Test
    void getUserPasswordCorrect() {
        String expectedUserPassword = "123";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserPassword = user.getPassword();

        Assertions.assertEquals(expectedUserPassword, actualUserPassword);
    }

    @Test
    void getUserPasswordIncorrect() {
        String expectedUserPassword = "12";

        User user = repository.getUser("thomløv@alpha.com", "123");
        String actualUserPassword = user.getPassword();

        Assertions.assertNotEquals(expectedUserPassword, actualUserPassword);
    }

    @Test
    void getUserRoleIDCorrect() {
        int expectedUserRoleID = 1;

        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserRoleID = user.getRoleID();

        Assertions.assertEquals(expectedUserRoleID, actualUserRoleID);
    }

    @Test
    void getUserRoleIDIncorrect() {
        int expectedUserRoleID = 2;

        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserRoleID = user.getRoleID();

        Assertions.assertNotEquals(expectedUserRoleID, actualUserRoleID);
    }

    @Test
    void createUserCorrect() {
        String email = "hejsa@alpha.com";
        String password = "123";

        repository.createUser(new User(0,"hejsa", "med dejsa", email, password, 2));
        User user = repository.getUser(email, password);

        Assertions.assertNotNull(user);
    }

    @Test
    void createUserIncorrect() {
        String email = "";
        String password = "123";

        Assertions.assertThrows(RuntimeException.class, () -> {
            repository.createUser(new User(0,"hejsa", "med dejsa", email, password, 2));
        });
    }

    @Test
    void getRoleOne() {
        String expectedRole = "Admin";

        String actualRole = repository.getRole(1);

        Assertions.assertEquals(expectedRole, actualRole);
    }

    @Test
    void getRoleOneIncorrect() {
        String expectedRole = "User";

        String actualRole = repository.getRole(1);

        Assertions.assertNotEquals(expectedRole, actualRole);
    }

    @Test
    void updateUserFirstNameCorrect() {
        String expectedUserFirstName = "hejsa";

        User user = repository.getUser("thomløv@alpha.com", "123");
        user.setFirstName(expectedUserFirstName);
        repository.updateUser(user);
        User newUser = repository.getUser("thomløv@alpha.com", "123");
        String actualUserFirstName = newUser.getFirstName();

        Assertions.assertEquals(expectedUserFirstName, actualUserFirstName);
    }

    @Test
    void updateUserFirstNameIncorrect() {
        String expectedUserFirstName = null;

        User user = repository.getUser("thomløv@alpha.com", "123");

        Assertions.assertThrows(RuntimeException.class, () -> {
            user.setFirstName(expectedUserFirstName);
            repository.updateUser(user);
        });
    }

    @Test
    void updateUserLastName() {
        String expectedUserLastName = "hejsa";

        User user = repository.getUser("thomløv@alpha.com", "123");
        user.setLastName(expectedUserLastName);
        repository.updateUser(user);
        User newUser = repository.getUser("thomløv@alpha.com", "123");
        String actualUserLastName = newUser.getLastName();

        Assertions.assertEquals(expectedUserLastName, actualUserLastName);
    }

    @Test
    void updateUserLastNameIncorrect() {
        String expectedUserLastName = null;

        User user = repository.getUser("thomløv@alpha.com", "123");
        Assertions.assertThrows(RuntimeException.class, () -> {
            user.setLastName(expectedUserLastName);
            repository.updateUser(user);
        });
    }

    @Test
    void updateUserPasswordCorrect() {
        String expectedUserPassword = "1234";

        User user = repository.getUser("thomløv@alpha.com", "123");
        user.setPassword(expectedUserPassword);
        repository.updateUser(user);
        User newUser = repository.getUser("thomløv@alpha.com", expectedUserPassword);
        String actualUserPassword = newUser.getPassword();

        Assertions.assertEquals(expectedUserPassword, actualUserPassword);
    }

    @Test
    void updateUserPasswordIncorrect() {
        String expectedUserPassword = null;

        User user = repository.getUser("thomløv@alpha.com", "123");

        Assertions.assertThrows(RuntimeException.class, () -> {
                    user.setPassword(expectedUserPassword);
                    repository.updateUser(user);
        });
    }

    @Test
    void updateUserRoleIDCorrect() {
        int expectedUserRole = 2;

        User user = repository.getUser("thomløv@alpha.com", "123");
        user.setRoleID(2);
        repository.updateUser(user);
        User newUser = repository.getUser("thomløv@alpha.com", "123");
        int actualUserRole = newUser.getRoleID();

        Assertions.assertEquals(expectedUserRole, actualUserRole);
    }

    //Only for testing purposes
    @Test
    void getUserRoleCorrect() {
        int expectedUserRole = 1;

        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserRole = user.getRoleID();

        Assertions.assertEquals(expectedUserRole, actualUserRole);
    }

    @Test
    void getUserRoleIncorrect() {
        int expectedUserRole = 0;

        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserRole = user.getRoleID();

        Assertions.assertNotEquals(expectedUserRole, actualUserRole);
    }

    @Test
    void getRolesCorrect() {
        int expectedNumberOfRoles = 2;

        List<Role> roles = repository.getRoles();
        int actualNumberOfRoles = roles.size();

        Assertions.assertEquals(expectedNumberOfRoles, actualNumberOfRoles);
    }

    @Test
    void deleteUserCorrect() {
        User expectedUser = null;

        repository.deleteUser(1);
        User actualUser = repository.getUser("thomløv@alpha.com", "123");
        Assertions.assertEquals(expectedUser, actualUser);
    }
}