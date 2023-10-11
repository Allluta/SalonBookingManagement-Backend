package com.application.ScholManagementSystem.services.admin;

import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.enums.UserRole;
import com.application.ScholManagementSystem.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
        if(adminAccount == null){
            User admin = new User();
            admin.setEmail("admin@test.com");
            admin.setName("admin");
            admin.setRole(UserRole.ADMIN);
            admin.setPhoneNumber("736661339");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(admin);
        }

    }
}
