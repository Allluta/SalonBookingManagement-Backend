package com.application.ScholManagementSystem.controllers;

import com.application.ScholManagementSystem.entities.Notification;
import com.application.ScholManagementSystem.repositories.NotificationRepository;
import com.application.ScholManagementSystem.services.notification.NotificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
