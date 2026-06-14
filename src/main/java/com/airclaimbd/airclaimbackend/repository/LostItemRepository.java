package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {
    Optional<LostItem> findByBagTagNumber(String bagTagNumber);
    Optional<LostItem> findByTicketNumber(String ticketNumber);
}