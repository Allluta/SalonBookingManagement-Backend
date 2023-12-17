package com.application.HairSalonManagementSystem.repositories;

import com.application.HairSalonManagementSystem.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
