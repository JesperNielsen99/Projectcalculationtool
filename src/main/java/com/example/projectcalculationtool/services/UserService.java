package com.example.projectcalculationtool.services;

import com.example.projectcalculationtool.models.Role;
import com.example.projectcalculationtool.models.User;
import com.example.projectcalculationtool.repositories.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.createUser(user);
    }

    public List<Role> getRoles() {
        return userRepository.getRoles();
    }

    public String getRole(int roleID) {
        return userRepository.getRole(roleID);
    }

    public User getUser(String password, String email) {
        return userRepository.getUser(password, email);
    }

    public void deleteUser(int userID) {
        userRepository.deleteUser(userID);
    }


    public void updateUser(User user) {
        userRepository.updateUser(user);
    }
}
