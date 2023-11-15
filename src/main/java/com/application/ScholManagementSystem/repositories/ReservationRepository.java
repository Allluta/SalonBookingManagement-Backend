package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEmail(String email);

    @Query("SELECT r FROM Reservation r WHERE r.hairdresserId = :hairdresserId AND r.date >= CURRENT_DATE ORDER BY r.date ASC")
    List<Reservation> findHairdresserUpcomingReservations(@Param("hairdresserId") Long hairdresserId);


}