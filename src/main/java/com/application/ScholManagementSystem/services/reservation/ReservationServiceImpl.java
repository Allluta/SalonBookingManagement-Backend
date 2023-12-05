package com.application.ScholManagementSystem.services.reservation;

import com.application.ScholManagementSystem.dto.ReservationRequest;
import com.application.ScholManagementSystem.entities.Notification;
import com.application.ScholManagementSystem.entities.Reservation;
import com.application.ScholManagementSystem.repositories.NotificationRepository;
import com.application.ScholManagementSystem.repositories.ReservationRepository;
import com.application.ScholManagementSystem.services.notification.NotificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private NotificationServiceImpl notificationServiceImpl;
    @Autowired
    private NotificationRepository notificationRepository;


    public Reservation createReservation(ReservationRequest request) {
        Reservation reservation = new Reservation();
        reservation.setServiceId(request.getServiceId());
        reservation.setHairdresserId(request.getHairdresserId());
        reservation.setDate(request.getDate());
        reservation.setTime(request.getTime());
        reservation.setEmail(request.getEmail());
        reservation.setPhoneNumber(request.getPhoneNumber());
        reservation.setStatus("Niepotwierdzona");

        sendNotificationToUser(reservation);

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(String userEmail) {
        return reservationRepository.findByEmail(userEmail);
    }

    public Optional<Reservation> getReservation(Long reservationId) {
        return reservationRepository.findById(reservationId);
    }
 public List<Reservation> getAllReservations(){
        return reservationRepository.findAll();
 }

    public void updateReservation(Long reservationId, String status, LocalDate newDate, String newTime) {
        try {
            Optional<Reservation> existingReservation = reservationRepository.findById(reservationId);
            if (existingReservation.isPresent()) {
                Reservation reservation = existingReservation.get();
                reservation.setStatus(status);

                if (newDate != null) {
                    reservation.setDate(newDate);
                }
                if (newTime != null) {
                    reservation.setTime(newTime);
                }

                reservationRepository.save(reservation);
                sendNotificationToUser(reservation);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public void cancelReservation(Long reservationId) {
        updateReservation(reservationId, "Zrezygnowano", null, null);
    }

    public boolean isReservationAvailable(Long hairdresserId, LocalDate date, String time) {

        List<Reservation> existingReservations = reservationRepository.checkReservationAvailability(hairdresserId, date, time);

        return existingReservations.isEmpty();
    }
    public List<Reservation> getHairdresserCompletedReservations(Long hairdresserId) {
        return reservationRepository.findHairdresserCompletedReservations(hairdresserId);
    }

    public List<Reservation> getHairdresserUpcomingReservations(Long hairdresserId) {
        return reservationRepository.findHairdresserUpcomingReservations(hairdresserId);
    }

    public List<Reservation> getHairdresserAllReservations(Long hairdresserId) {
        return reservationRepository.findHairdresserAllReservations(hairdresserId);
    }

    private void sendNotificationToUser(Reservation reservation) {
        String userEmail = reservation.getEmail();
        String status = reservation.getStatus().toLowerCase();
        String message = "";

        switch (status) {
            case "niepotwierdzona":
                message = "Dziękujemy za rezerwację. Prosimy oczekiwać na potwierdzenie.";
                break;
            case "zatwierdzona":
                message = "Twoja rezerwacja została zatwierdzona.";
                break;
            case "odrzucona":
                message = "Przepraszamy, rezerwacja została odrzucona. Prosimy spróbować ponownie lub skontaktować się z salonem.";
                break;
            default:
                break;
        }

        Notification notification = new Notification();
        notification.setDate(LocalDate.now().toString());
        notification.setMessage(message);
        notification.setUserEmail(userEmail);

        notificationRepository.save(notification);
    }


}
