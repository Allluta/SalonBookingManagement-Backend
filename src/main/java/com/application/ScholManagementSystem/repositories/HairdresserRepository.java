package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HairdresserRepository extends JpaRepository<Hairdresser, Long> {

    Optional<Hairdresser> findFirstByEmail(String email);

}
