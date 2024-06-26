package com.application.HairSalonManagementSystem.services.notification;

import com.application.HairSalonManagementSystem.dto.NotificationRequest;
import com.application.HairSalonManagementSystem.entities.Notification;
import com.application.HairSalonManagementSystem.entities.User;
import com.application.HairSalonManagementSystem.repositories.NotificationRepository;
import com.application.HairSalonManagementSystem.repositories.UserRepository;
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

    public void sendNotificationToUser(NotificationRequest request, String message) {
        Notification notification = new Notification();

        notification.setDate(LocalDate.now().toString());
        notification.setTime(LocalTime.now());
        notification.setMessage(message);

        Optional<User> user = userRepository.findFirstByEmail(request.getUserEmail());
        user.ifPresent(u -> {
            notification.setUserEmail(request.getUserEmail());
            notificationRepository.save(notification);
        });
    }
        public List<Notification> getNotificationsForUser(String userEmail) {
            return notificationRepository.findByUserEmail(userEmail);
        }

}
