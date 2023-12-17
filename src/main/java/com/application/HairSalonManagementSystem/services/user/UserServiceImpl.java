package com.application.HairSalonManagementSystem.services.user;

import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.enums.UserRole;
import com.application.HairSalonManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;


    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getAllUsers() {
        return userRepository.findByRole(UserRole.USER);
    }

    }

