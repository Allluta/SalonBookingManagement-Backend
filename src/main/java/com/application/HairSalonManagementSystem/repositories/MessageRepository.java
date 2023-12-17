package com.application.HairSalonManagementSystem.repositories;

import com.application.HairSalonManagementSystem.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Jeśli potrzebujesz niestandardowe zapytania, możesz je dodać tutaj
}
