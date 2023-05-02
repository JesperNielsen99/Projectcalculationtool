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
}
