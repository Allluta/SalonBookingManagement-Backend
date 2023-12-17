package com.application.HairSalonManagementSystem.controllers;

import com.application.HairSalonManagementSystem.entities.Hairdresser;
import com.application.HairSalonManagementSystem.entities.Review;
import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.enums.UserRole;
import com.application.HairSalonManagementSystem.repositories.HairdresserRepository;
import com.application.HairSalonManagementSystem.repositories.UserRepository;
import com.application.HairSalonManagementSystem.services.hairdresser.HairdresserServiceImpl;
import com.application.HairSalonManagementSystem.services.review.ReviewServiceImpl;
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

private final ReviewServiceImpl reviewServiceImpl;
    public HairdresserController(HairdresserServiceImpl hairdresserServiceImpl, UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, HairdresserRepository hairdresserRepository, ReviewServiceImpl reviewServiceImpl) {
        this.hairdresserServiceImpl = hairdresserServiceImpl;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.hairdresserRepository = hairdresserRepository;
        this.reviewServiceImpl = reviewServiceImpl;
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
    @GetMapping("/{id}/reviews")
    public List<Review> getHairdresserReviews(@PathVariable Long id) {
        return reviewServiceImpl.getReviewsByHairdresserId(id);
    }
}
