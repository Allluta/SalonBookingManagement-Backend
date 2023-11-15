package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.dto.ReservationRequest;
import com.application.ScholManagementSystem.entities.Reservation;
import com.application.ScholManagementSystem.repositories.ReservationRepository;
import com.application.ScholManagementSystem.services.reservation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationServiceImpl reservationServiceImpl;

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationRequest request) {
        try {
            Reservation reservation = reservationServiceImpl.createReservation(request);
            return ResponseEntity.ok(reservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas tworzenia rezerwacji.");
        }
    }

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<?> getUserReservations(@PathVariable String userEmail) {
        try {
            List<Reservation> userReservations = reservationServiceImpl.getUserReservations(userEmail);
            return ResponseEntity.ok(userReservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania rezerwacji użytkownika.");
        }
    }
    @GetMapping("/{reservationId}")
    public ResponseEntity<?> getReservation(@PathVariable Long reservationId) {
        try {
            Optional<Reservation> reservation = reservationServiceImpl.getReservation(reservationId);
            return reservation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania szczegółów rezerwacji.");
        }
    }
    @GetMapping("/hairdresser/{hairdresserId}/upcoming")
    public ResponseEntity<?> getHairdresserUpcomingReservations(@PathVariable Long hairdresserId) {
        try {
            List<Reservation> hairdresserUpcomingReservations = reservationServiceImpl.getHairdresserUpcomingReservations(hairdresserId);
            return ResponseEntity.ok(hairdresserUpcomingReservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania nadchodzących rezerwacji fryzjera.");
        }
    }

}
