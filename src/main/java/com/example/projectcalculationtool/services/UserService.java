package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public void createUser(User user) {
        repository.createUser(user);
    }

    public List<Role> getRoles() {
        return repository.getRoles();
    }

    public String getRole(int roleID) {
        return repository.getRole(roleID);
    }

    public User getUser(String password, String email) {
        return repository.getUser(password, email);
    }

    public void deleteUser(int userID) {
        repository.deleteUser(userID);
    }


    public void updateUser(User user) {
        repository.updateUser(user);
    }
}
