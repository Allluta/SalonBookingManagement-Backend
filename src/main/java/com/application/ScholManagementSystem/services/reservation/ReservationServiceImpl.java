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

    public List<Reservation> getHairdresserUpcomingReservations(Long hairdresserId) {
        return reservationRepository.findHairdresserUpcomingReservations(hairdresserId);
    }

    public void updateReservationStatus(Long reservationId, String status) {
        Optional<Reservation> existingReservation = reservationRepository.findById(reservationId);
        existingReservation.ifPresent(reservation -> {
            System.out.println("Przed aktualizacją: " + reservation.getStatus());
            reservation.setStatus(status);
            reservationRepository.save(reservation);
            System.out.println("Po aktualizacji: " + reservation.getStatus());
            sendNotificationToUser(reservation);
        });
    }
    public void cancelReservation(Long reservationId) {
        updateReservationStatus(reservationId, "Zrezygnowano");
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
                message = "Twoja rezerwacja została zatwierdzona. Cieszymy się na spotkanie!";
                break;
            case "odrzucona":
                message = "Przepraszamy, rezerwacja została odrzucona. Prosimy spróbować ponownie lub skontaktować się z salonem.";
                break;
            default:
                message = "Dziękujemy za rezerwację.";
                break;
        }

        Notification notification = new Notification();
        notification.setDate(LocalDate.now().toString());
        notification.setMessage(message);
        notification.setUserEmail(userEmail);

        notificationRepository.save(notification);
    }

    public boolean isReservationAvailable(Long hairdresserId, LocalDate date, String time) {

        List<Reservation> existingReservations = reservationRepository.checkReservationAvailability(hairdresserId, date, time);

        return existingReservations.isEmpty();
    }
    public List<Reservation> getHairdresserCompletedReservations(Long hairdresserId) {
        return reservationRepository.findHairdresserCompletedReservations(hairdresserId);
    }

}
