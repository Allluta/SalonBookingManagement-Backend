package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // Jeśli potrzebujesz niestandardowe zapytania, możesz je dodać tutaj
}
