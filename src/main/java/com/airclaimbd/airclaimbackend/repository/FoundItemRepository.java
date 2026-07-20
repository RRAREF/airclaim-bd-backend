package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {

    // ==========================
    // Dashboard
    // ==========================

    long countByStatus(String status);

    long countByEmail(String email);

    long countByEmailAndStatus(String email, String status);

    // ==========================
    // User Reports
    // ==========================

    List<FoundItem> findByEmail(String email);

    // ==========================
    // Matching
    // ==========================

    Optional<FoundItem> findByBagTagNumberAndTicketNumber(
            String bagTagNumber,
            String ticketNumber
    );

    // ==========================
    // Duplicate Check
    // ==========================

    boolean existsByBagTagNumber(String bagTagNumber);

    boolean existsByTicketNumber(String ticketNumber);

    // ==========================
    // Live Search
    // ==========================

    List<FoundItem> findByBagTagNumberContainingIgnoreCase(String keyword);

    List<FoundItem> findByTicketNumberContainingIgnoreCase(String keyword);

}