package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.enums.UserRole;
import com.application.ScholManagementSystem.repositories.UserRepository;
import com.application.ScholManagementSystem.services.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findByRole(UserRole.USER);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {

        userServiceImpl.deleteUser(id);
    }
}

