package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.exceptions.UserAlreadyExistsException;
import com.application.ScholManagementSystem.services.RegistrationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private final RegistrationService registrationService;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(RegistrationService registrationService, BCryptPasswordEncoder passwordEncoder) {
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        responseHeaders.set("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        responseHeaders.set("Access-Control-Allow-Headers", "Content-Type");
        try {
            User registeredUser = registrationService.registerUser(user);
            return ResponseEntity.ok("{\"message\": \"Registration successful\"}");
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
}
