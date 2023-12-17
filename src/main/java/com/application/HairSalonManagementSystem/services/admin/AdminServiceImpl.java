package com.application.HairSalonManagementSystem.services.admin;

import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.enums.UserRole;
import com.application.HairSalonManagementSystem.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount(){
        List<User> adminAccounts = userRepository.findByRole(UserRole.ADMIN);
        if(adminAccounts.isEmpty()){
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
