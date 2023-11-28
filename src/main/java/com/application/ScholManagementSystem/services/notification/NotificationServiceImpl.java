package com.application.ScholManagementSystem.services.notification;

import com.application.ScholManagementSystem.entities.Notification;
import com.application.ScholManagementSystem.entities.User;
import com.application.ScholManagementSystem.repositories.NotificationRepository;
import com.application.ScholManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public void sendNotificationToUser(String userEmail, String message) {
        Notification notification = new Notification();

        notification.setDate(LocalDate.now().toString());
        notification.setTime(LocalTime.now());
        notification.setMessage(message);

        Optional<User> user = userRepository.findFirstByEmail(userEmail);
        user.ifPresent(u -> {
            notificationRepository.save(notification);
        });

    }
        public List<Notification> getNotificationsForUser(String userEmail) {
            return notificationRepository.findByUserEmail(userEmail);
        }

}
