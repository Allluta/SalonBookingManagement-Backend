package com.application.HairSalonManagementSystem.repositories;

import com.application.HairSalonManagementSystem.entities.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HairdresserRepository extends JpaRepository<Hairdresser, Long> {

    Optional<Hairdresser> findFirstByEmail(String email);

}
