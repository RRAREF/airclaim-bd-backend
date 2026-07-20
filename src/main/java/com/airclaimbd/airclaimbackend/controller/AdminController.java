package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.dto.AdminLoginRequest;
import com.airclaimbd.airclaimbackend.dto.AdminLoginResponse;
import com.airclaimbd.airclaimbackend.dto.DashboardStats;
import com.airclaimbd.airclaimbackend.entity.Admin;
import com.airclaimbd.airclaimbackend.entity.FoundItem;
import com.airclaimbd.airclaimbackend.entity.LostItem;
import com.airclaimbd.airclaimbackend.entity.User;
import com.airclaimbd.airclaimbackend.repository.AdminRepository;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import com.airclaimbd.airclaimbackend.repository.UserRepository;
import com.airclaimbd.airclaimbackend.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LostItemRepository lostItemRepository;

    @Autowired
    private FoundItemRepository foundItemRepository;

    @Autowired
    private DashboardService dashboardService;

    // ==========================
    // Admin Login
    // ==========================

    @PostMapping("/login")
    public AdminLoginResponse login(
            @RequestBody AdminLoginRequest request
    ) {

        Admin admin = adminRepository.findByUsernameAndPassword(
                request.getUsername(),
                request.getPassword()
        );

        if (admin == null) {

            return new AdminLoginResponse(
                    false,
                    "",
                    "Invalid username or password."
            );

        }

        return new AdminLoginResponse(
                true,
                admin.getUsername(),
                "Login successful."
        );

    }

    // ==========================
    // Dashboard
    // ==========================

    @GetMapping("/dashboard")
    public DashboardStats getDashboard() {

        return dashboardService.getDashboardStats();

    }

    // ==========================
    // User Management
    // ==========================

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id
    ) {

        if (!userRepository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body("User not found.");

        }

        userRepository.deleteById(id);

        return ResponseEntity.ok("User deleted successfully.");

    }

    // ==========================
    // Lost Reports
    // ==========================

    @GetMapping("/lost-items")
    public List<LostItem> getAllLostItems() {

        return lostItemRepository.findAll();

    }

    @DeleteMapping("/lost-items/{id}")
    public ResponseEntity<String> deleteLostItem(
            @PathVariable Long id
    ) {

        if (!lostItemRepository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body("Lost report not found.");

        }

        lostItemRepository.deleteById(id);

        return ResponseEntity.ok("Lost report deleted successfully.");

    }

    // ==========================
    // Found Reports
    // ==========================

    @GetMapping("/found-items")
    public List<FoundItem> getAllFoundItems() {

        return foundItemRepository.findAll();

    }

    @DeleteMapping("/found-items/{id}")
    public ResponseEntity<String> deleteFoundItem(
            @PathVariable Long id
    ) {

        if (!foundItemRepository.existsById(id)) {

            return ResponseEntity
                    .badRequest()
                    .body("Found report not found.");

        }

        foundItemRepository.deleteById(id);

        return ResponseEntity.ok("Found report deleted successfully.");

    }

}