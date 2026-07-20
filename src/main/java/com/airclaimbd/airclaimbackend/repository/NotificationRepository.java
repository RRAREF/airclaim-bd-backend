package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByEmailOrderByCreatedAtDesc(String email);

    long countByEmailAndIsReadFalse(String email);

}