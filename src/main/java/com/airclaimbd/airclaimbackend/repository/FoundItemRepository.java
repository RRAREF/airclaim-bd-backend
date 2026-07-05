package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {

    // Dashboard statistics
    long countByStatus(String status);

    // Automatic matching
    Optional<FoundItem> findByBagTagNumberAndTicketNumber(
            String bagTagNumber,
            String ticketNumber
    );

}