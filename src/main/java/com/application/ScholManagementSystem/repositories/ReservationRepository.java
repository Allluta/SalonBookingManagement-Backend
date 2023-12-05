package com.application.ScholManagementSystem.repositories;

import com.application.ScholManagementSystem.entities.Reservation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEmail(String email);

    @Query("SELECT r FROM Reservation r WHERE r.hairdresserId = :hairdresserId AND r.date >= CURRENT_DATE ORDER BY r.date ASC")
    List<Reservation> findHairdresserUpcomingReservations(@Param("hairdresserId") Long hairdresserId);

    @Transactional
    @Modifying
    @Query("UPDATE Reservation r SET r.status = :status WHERE r.id = :reservationId")
    void updateReservationStatus(@Param("reservationId") Long reservationId, @Param("status") String status);

    @Query("SELECT r FROM Reservation r WHERE r.hairdresserId = :hairdresserId AND r.date = :date AND r.time = :time")
    List<Reservation> checkReservationAvailability(
            @Param("hairdresserId") Long hairdresserId,
            @Param("date") LocalDate date,
            @Param("time") String time
    );

    @Query("SELECT r FROM Reservation r WHERE r.hairdresserId = :hairdresserId AND r.date < CURRENT_DATE ORDER BY r.date DESC")
    List<Reservation> findHairdresserCompletedReservations(@Param("hairdresserId") Long hairdresserId);
    @Query("SELECT r FROM Reservation r WHERE r.hairdresserId = :hairdresserId")
    List<Reservation> findHairdresserAllReservations(@Param("hairdresserId") Long hairdresserId);

}
