package com.airclaimbd.airclaimbackend.service;

import com.airclaimbd.airclaimbackend.dto.DashboardStats;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import com.airclaimbd.airclaimbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LostItemRepository lostRepository;

    @Autowired
    private FoundItemRepository foundRepository;

    // ===========================
    // Home Page Statistics
    // ===========================

    public DashboardStats getDashboardStats() {

        long totalUsers = userRepository.count();

        long totalLostReports = lostRepository.count();

        long totalFoundReports = foundRepository.count();

        long matchedReports =
                lostRepository.countByStatus("Matched")
                        + foundRepository.countByStatus("Matched");

        long pendingReports =
                lostRepository.countByStatus("Pending")
                        + foundRepository.countByStatus("Pending");

        return new DashboardStats(
                totalUsers,
                totalLostReports,
                totalFoundReports,
                matchedReports,
                pendingReports
        );
    }

    // ===========================
    // Logged In User Statistics
    // ===========================

    public DashboardStats getUserDashboardStats(String email) {

        long lostReports =
                lostRepository.countByEmail(email);

        long foundReports =
                foundRepository.countByEmail(email);

        long matchedReports =
                lostRepository.countByEmailAndStatus(email, "Matched")
                        + foundRepository.countByEmailAndStatus(email, "Matched");

        long pendingReports =
                lostRepository.countByEmailAndStatus(email, "Pending")
                        + foundRepository.countByEmailAndStatus(email, "Pending");

        return new DashboardStats(
                0,
                lostReports,
                foundReports,
                matchedReports,
                pendingReports
        );
    }

}