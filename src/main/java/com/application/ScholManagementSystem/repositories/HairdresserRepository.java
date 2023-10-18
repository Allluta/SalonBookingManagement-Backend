package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairdresserRepository extends JpaRepository<Hairdresser, Long> {



}
