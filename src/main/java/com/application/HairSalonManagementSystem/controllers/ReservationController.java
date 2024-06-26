package com.application.HairSalonManagementSystem.controllers;

import com.application.HairSalonManagementSystem.dto.ReservationRequest;
import com.application.HairSalonManagementSystem.entities.Reservation;
import com.application.HairSalonManagementSystem.services.reservation.ReservationServiceImpl;
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
            boolean isAvailable = reservationServiceImpl.isReservationAvailable(request.getHairdresserId(), request.getDate(), request.getTime());

            if (isAvailable) {
                request.setStatus("Niepotwierdzona");
                Reservation reservation = reservationServiceImpl.createReservation(request);
                return ResponseEntity.ok(reservation);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dana usługa o wybranej porze jest niedostępna.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas tworzenia rezerwacji.");
        }
    }

    @GetMapping
    public List<Reservation> getAllReservations(){
        return reservationServiceImpl.getAllReservations();
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
    @PutMapping("/{reservationId}")
    public ResponseEntity<?> updateReservation(@PathVariable Long reservationId, @RequestBody ReservationRequest request) {
        try {
            System.out.println("Received request to update reservation. Reservation ID: " + reservationId + ", New date: " + request.getDate());

            Optional<Reservation> existingReservation = reservationServiceImpl.getReservation(reservationId);
            if (existingReservation.isPresent()) {
                Reservation reservation = existingReservation.get();
                reservation.setStatus(request.getStatus());


                if (request.getDate() != null) {
                    reservationServiceImpl.updateReservation(
                            reservation.getId(),
                            request.getStatus(),
                            request.getDate(),
                            request.getTime());
                } else {
                    reservationServiceImpl.updateReservation(reservation.getId(), request.getStatus(), reservation.getDate(), reservation.getTime());
                }

                System.out.println("Reservation updated successfully. New status: " + request.getStatus() + ", New date: " + reservation.getDate());

                return ResponseEntity.ok(reservation);
            } else {
                System.out.println("Reservation not found.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            System.out.println("Error updating reservation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas aktualizacji rezerwacji.");
        }
    }
    @PutMapping("/cancel/{reservationId}")
    public ResponseEntity<?> cancelReservation(@PathVariable Long reservationId) {
        try {
            reservationServiceImpl.updateReservation(reservationId, "Zrezygnowano", null, null);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas anulowania rezerwacji.");
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
    @GetMapping("/hairdresser/{hairdresserId}/completed")
    public ResponseEntity<?> getHairdresserCompletedReservations(@PathVariable Long hairdresserId) {
        try {
            List<Reservation> hairdresserCompletedReservations = reservationServiceImpl.getHairdresserCompletedReservations(hairdresserId);
            return ResponseEntity.ok(hairdresserCompletedReservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania odbytych rezerwacji fryzjera.");
        }
    }
    @GetMapping("/hairdresser/{hairdresserId}/all")
    public ResponseEntity<?> getHairdresserAllReservations(@PathVariable Long hairdresserId) {
        try {
            List<Reservation> hairdresserAllReservations = reservationServiceImpl.getHairdresserAllReservations(hairdresserId);
            return ResponseEntity.ok(hairdresserAllReservations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania wszystkich rezerwacji fryzjera.");
        }
    }


}





