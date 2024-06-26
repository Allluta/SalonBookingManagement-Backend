package com.application.HairSalonManagementSystem.services;

import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.enums.UserRole;
import com.application.HairSalonManagementSystem.exceptions.UserAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.application.HairSalonManagementSystem.repositories.UserRepository;

import java.util.Optional;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        Optional<User> existingUser = userRepository.findFirstByEmail(user.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Użytkownik o podanym adresie email juz istnieje.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }



}
