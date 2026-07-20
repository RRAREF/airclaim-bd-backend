package com.airclaimbd.airclaimbackend.service;

import com.airclaimbd.airclaimbackend.entity.Notification;
import com.airclaimbd.airclaimbackend.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    // ==========================
    // Create Notification
    // ==========================

    public void createNotification(
            String email,
            String title,
            String message,
            String type
    ) {

        System.out.println("========== CREATE NOTIFICATION ==========");
        System.out.println("Email   : " + email);
        System.out.println("Title   : " + title);
        System.out.println("Message : " + message);
        System.out.println("Type    : " + type);

        Notification notification = new Notification(
                email,
                title,
                message,
                type
        );

        notificationRepository.save(notification);

        System.out.println("Notification saved successfully.");
        System.out.println("=========================================");

    }

    // ==========================
    // Get Notifications
    // ==========================

    public List<Notification> getNotifications(String email) {
        return notificationRepository.findByEmailOrderByCreatedAtDesc(email);
    }

    // ==========================
    // Count Unread
    // ==========================

    public long getUnreadCount(String email) {
        return notificationRepository.countByEmailAndIsReadFalse(email);
    }

    // ==========================
    // Mark As Read
    // ==========================

    public void markAsRead(Long id) {

        Notification notification =
                notificationRepository.findById(id).orElse(null);

        if (notification != null) {
            notification.setRead(true);
            notificationRepository.save(notification);
        }

    }

}