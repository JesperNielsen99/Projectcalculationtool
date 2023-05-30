package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
    private IUserRepository repository;

    @Autowired
    private UserTestDB userTestDB;

    @BeforeEach
    void setUp() {
        repository = new UserRepository();

        userTestDB.userTestDB();
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
    void updateUserLastNameCorrect() {
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