package com.application.HairSalonManagementSystem.repositories;

import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{


    List<User> findByRole(UserRole role);
    Optional<User> findFirstByEmail(String email);
}
