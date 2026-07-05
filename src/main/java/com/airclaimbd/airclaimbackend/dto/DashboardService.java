package com.airclaimbd.airclaimbackend.service;

import com.airclaimbd.airclaimbackend.dto.DashboardStats;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private LostItemRepository lostRepository;

    @Autowired
    private FoundItemRepository foundRepository;

    public DashboardStats getDashboardStats() {

        long totalLost = lostRepository.count();

        long totalFound = foundRepository.count();

        long matched =
                lostRepository.countByStatus("Matched")
                        + foundRepository.countByStatus("Matched");

        long pending =
                lostRepository.countByStatus("Pending")
                        + foundRepository.countByStatus("Pending");

        return new DashboardStats(
                totalLost,
                totalFound,
                matched,
                pending
        );
    }
}