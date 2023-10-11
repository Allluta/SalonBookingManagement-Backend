package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.exceptions.UserAlreadyExistsException;
import com.application.ScholManagementSystem.services.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        try {
            User registeredUser = registrationService.registerUser(user);
            return ResponseEntity.ok("Registration successful");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
}
