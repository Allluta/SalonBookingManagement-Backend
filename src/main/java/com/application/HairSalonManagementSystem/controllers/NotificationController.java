package com.application.HairSalonManagementSystem.controllers;

import com.application.HairSalonManagementSystem.dto.NotificationRequest;
import com.application.HairSalonManagementSystem.entities.Notification;
import com.application.HairSalonManagementSystem.repositories.NotificationRepository;
import com.application.HairSalonManagementSystem.services.notification.NotificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationRepository notificationRepository;

    private final NotificationServiceImpl notificationServiceImpl;
    public NotificationController(NotificationRepository notificationRepository, NotificationServiceImpl notificationServiceImpl) {
        this.notificationRepository = notificationRepository;
        this.notificationServiceImpl = notificationServiceImpl;
    }
    @GetMapping
    public List<Notification> getNotifications() {

        return notificationRepository.findAll();
    }
    @GetMapping("/user/{userEmail}")
    public ResponseEntity<?> getNotificationsForUser(@PathVariable String userEmail) {
        try {
            List<Notification> userNotifications = notificationServiceImpl.getNotificationsForUser(userEmail);
            return ResponseEntity.ok(userNotifications);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Błąd podczas pobierania powiadomień użytkownika.");
        }
    }
    @PostMapping("/interest")
    public ResponseEntity<?> registerInterest(@RequestBody NotificationRequest request) {
        try {
            String userEmail = request.getUserEmail();
            System.out.println("Otrzymano rejestrację zainteresowania dla adresu e-mail: " + userEmail);
            notificationServiceImpl.sendNotificationToUser(request, "Termin, którym była Pani zainteresowana : 18.12.2023 r. o godzinie 12:00, u Pani Małgorzaty,   zwolnił się! Zapraszamy !");
            return ResponseEntity.ok(Map.of("message", "Zainteresowanie zarejestrowane pomyślnie"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Błąd podczas rejestracji zainteresowania"));
        }
    }

}
