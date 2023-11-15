package com.application.ScholManagementSystem.services.reservation;

import com.application.ScholManagementSystem.dto.ReservationRequest;
import com.application.ScholManagementSystem.entities.Reservation;
import com.application.ScholManagementSystem.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setServiceId(request.getServiceId());
        reservation.setHairdresserId(request.getHairdresserId());
        reservation.setDate(request.getDate());
        reservation.setTime(request.getTime());
        reservation.setEmail(request.getEmail());
        reservation.setPhoneNumber(request.getPhoneNumber());

        // Dodaj inne niezbędne dane rezerwacji

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(String userEmail) {
        return reservationRepository.findByEmail(userEmail);
    }

    public Optional<Reservation> getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
    public List<Reservation> getHairdresserUpcomingReservations(Long hairdresserId) {
        // Tutaj implementuj logikę pobierania nadchodzących rezerwacji dla danego fryzjera
        // Użyj odpowiedniego zapytania do bazy danych
        return reservationRepository.findHairdresserUpcomingReservations(hairdresserId);
    }




}

