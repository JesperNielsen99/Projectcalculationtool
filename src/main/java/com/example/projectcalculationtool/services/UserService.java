package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public void createUser(User user) {
        repository.createUser(user);
    }
}
