package com.example.projectcalculationtool.repositories.interfaces;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;

import java.util.List;

public interface IUserRepository {
    void createUser(User user);
    List<Role> getRoles();
    String getRole(int roleID);

    User getUser(String password, String email);
}
