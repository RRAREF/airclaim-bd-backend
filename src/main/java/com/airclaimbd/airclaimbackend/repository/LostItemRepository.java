package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.LostItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LostItemRepository extends JpaRepository<LostItem, Long> {

    // ===========================
    // Matching
    // ===========================

    Optional<LostItem> findByBagTagNumber(String bagTagNumber);

    Optional<LostItem> findByTicketNumber(String ticketNumber);

    Optional<LostItem> findByBagTagNumberAndTicketNumber(
            String bagTagNumber,
            String ticketNumber
    );

    // ===========================
    // User Reports
    // ===========================

    List<LostItem> findByEmail(String email);

    long countByEmail(String email);

    long countByEmailAndStatus(String email, String status);

    // ===========================
    // Dashboard
    // ===========================

    long countByStatus(String status);

    // ===========================
    // Duplicate Check
    // ===========================

    boolean existsByBagTagNumber(String bagTagNumber);

    boolean existsByTicketNumber(String ticketNumber);

    // ===========================
    // Live Suggestions
    // ===========================

    List<LostItem> findTop5ByBagTagNumberStartingWithIgnoreCase(
            String bagTagNumber
    );

    List<LostItem> findTop5ByTicketNumberStartingWithIgnoreCase(
            String ticketNumber
    );

}