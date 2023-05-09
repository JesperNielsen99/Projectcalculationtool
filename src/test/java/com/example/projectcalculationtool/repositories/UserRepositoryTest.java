package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.*;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {
    private IUserRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepository();
    }

    @Test
    void getUser() {
        int expectedUserID = 1;
        User user = repository.getUser("thomløv@alpha.com", "123");
        int actualUserID = user.getUserID();
        Assertions.assertEquals(expectedUserID, actualUserID);
    }

    @Test
    void createUser() {

    }

    @Test
    void numberOfRoles() {
        int expectedNumberOfRoles = 2;
        List<Role> roles = repository.getRoles();
        int actualNumberOfRoles  = roles.size();
        Assertions.assertEquals(expectedNumberOfRoles, actualNumberOfRoles);
    }

    @Test
    void getRoleOne() {
        String expected = "Admin";
        String actual = repository.getRole(1);
        assert(expected.equals(actual));
    }

    @Test
    void getRoleTwo() {
        String expected = "User";
        String actual = repository.getRole(2);
        assert(expected.equals(actual));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}