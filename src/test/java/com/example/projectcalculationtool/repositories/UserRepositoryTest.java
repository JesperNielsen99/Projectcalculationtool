package com.example.projectcalculationtool.repositories;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
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
    void createUser() {

    }

    @Test
    void getRoles() {
        int expected = 2;
        List<Role> roles = repository.getRoles();
        int actual = roles.size();
        assert(expected == actual);
    }

    @Test
    void getRole() {
        String expected = "Admin";
        String actual = repository.getRole(1);
        assert(expected.equals(actual));
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void getUser() {
    }
}