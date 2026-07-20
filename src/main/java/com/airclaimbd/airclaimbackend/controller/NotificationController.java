package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.entity.Notification;
import com.airclaimbd.airclaimbackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // ==========================
    // Get All Notifications
    // ==========================

    @GetMapping("/{email}")
    public List<Notification> getNotifications(
            @PathVariable String email
    ) {

        return notificationService.getNotifications(email);

    }

    // ==========================
    // Get Unread Count
    // ==========================

    @GetMapping("/count/{email}")
    public long getUnreadCount(
            @PathVariable String email
    ) {

        return notificationService.getUnreadCount(email);

    }

    // ==========================
    // Mark As Read
    // ==========================

    @PutMapping("/read/{id}")
    public String markAsRead(
            @PathVariable Long id
    ) {

        notificationService.markAsRead(id);

        return "Notification marked as read.";

    }

}