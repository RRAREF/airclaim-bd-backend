package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.dto.DashboardStats;
import com.airclaimbd.airclaimbackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // Home Page (All Database Statistics)
    @GetMapping("/stats")
    public DashboardStats getDashboardStats() {
        return dashboardService.getDashboardStats();
    }

    // User Dashboard
    @GetMapping("/stats/{email}")
    public DashboardStats getUserDashboardStats(
            @PathVariable String email
    ) {
        return dashboardService.getUserDashboardStats(email);
    }

}