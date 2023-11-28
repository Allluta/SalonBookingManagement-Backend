package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.dto.VerifyPasswordRequest;
import com.application.ScholManagementSystem.entities.Hairdresser;
import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.enums.UserRole;
import com.application.ScholManagementSystem.repositories.HairdresserRepository;
import com.application.ScholManagementSystem.repositories.UserRepository;
import com.application.ScholManagementSystem.services.hairdresser.HairdresserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/hairdressers")

public class HairdresserController {
    private final HairdresserServiceImpl hairdresserServiceImpl;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
     private final HairdresserRepository hairdresserRepository;


    public HairdresserController(HairdresserServiceImpl hairdresserServiceImpl, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, HairdresserRepository hairdresserRepository) {
        this.hairdresserServiceImpl = hairdresserServiceImpl;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.hairdresserRepository = hairdresserRepository;
    }

    @PostMapping
    public Hairdresser createHairdresser(@RequestBody Hairdresser hairdresser) {

        User user = new User();
        user.setEmail(hairdresser.getEmail());
        user.setName(hairdresser.getName());
        user.setPhoneNumber(hairdresser.getPhoneNumber());
        user.setRole(UserRole.HAIRDRESSER);

        String hashedPassword = passwordEncoder.encode(hairdresser.getPassword());
        user.setPassword(hashedPassword);

        Hairdresser createdHairdresser = hairdresserServiceImpl.createHairdresser(hairdresser);
        Long hairdresserId = createdHairdresser.getId();
        user.setHairdresserId(hairdresserId);
        userRepository.save(user);
        return createdHairdresser;
    }

    @GetMapping
    public List<Hairdresser> getAllHairdressers() {
        return hairdresserServiceImpl.getAllHairdressers();
    }

    @PutMapping("/{id}")
    public Hairdresser updateHairdresser(@PathVariable Long id, @RequestBody Hairdresser hairdresser) {
        return hairdresserServiceImpl.updateHairdresser(id, hairdresser);
    }

    @DeleteMapping("/{id}")
    public void deleteHairdresser(@PathVariable Long id) {
        hairdresserServiceImpl.deleteHairdresser(id);
    }

    @GetMapping("/{id}")
    public Hairdresser getHairdresserById(@PathVariable Long id) {
        return hairdresserServiceImpl.getHairdresserById(id);
    }
}
