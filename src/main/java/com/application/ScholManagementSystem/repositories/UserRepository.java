package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


    User findByRole(UserRole admin);

    Optional<User> findFirstByEmail(String email);
}
