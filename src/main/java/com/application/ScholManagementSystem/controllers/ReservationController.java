package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.dto.ReservationRequest;
import com.application.ScholManagementSystem.entities.Reservation;
import com.application.ScholManagementSystem.repositories.ReservationRepository;
import com.application.ScholManagementSystem.services.reservation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}