package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.entity.LostItem;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import com.airclaimbd.airclaimbackend.service.MatchingService;
import com.airclaimbd.airclaimbackend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lost-items")
@CrossOrigin(origins = "*")
public class LostItemController {

    @Autowired
    private LostItemRepository lostRepo;

    @Autowired
    private MatchingService matchingService;

    @Autowired
    private NotificationService notificationService;

    // ==========================
    // Submit Lost Report
    // ==========================

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public LostItem createLostItem(

            @RequestParam String airportName,
            @RequestParam String passengerName,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String itemName,
            @RequestParam String itemDescription,
            @RequestParam String bagTagNumber,
            @RequestParam String ticketNumber,
            @RequestParam String dateLost,
            @RequestParam(required = false) MultipartFile image

    ) throws IOException {

        bagTagNumber = bagTagNumber.trim().toUpperCase();
        ticketNumber = ticketNumber.trim().toUpperCase();

        // Duplicate Bag Tag Check
        if (lostRepo.existsByBagTagNumber(bagTagNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Bag Tag Number already exists."
            );
        }

        // Duplicate Ticket Check
        if (lostRepo.existsByTicketNumber(ticketNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ticket Number already exists."
            );
        }

        LostItem lostItem = new LostItem();

        lostItem.setAirportName(airportName);
        lostItem.setPassengerName(passengerName);
        lostItem.setEmail(email);
        lostItem.setPhone(phone);
        lostItem.setItemName(itemName);
        lostItem.setItemDescription(itemDescription);
        lostItem.setBagTagNumber(bagTagNumber);
        lostItem.setTicketNumber(ticketNumber);
        lostItem.setDateLost(dateLost);
        lostItem.setStatus("Pending");

        if (image != null && !image.isEmpty()) {
            lostItem.setImage(image.getBytes());
        }

        LostItem savedItem = lostRepo.save(lostItem);

        // Create Notification
        notificationService.createNotification(
                savedItem.getEmail(),
                "Lost Report Submitted",
                "Your lost item report has been submitted successfully.",
                "SUCCESS"
        );

        // Check Matching
        matchingService.matchLostItem(savedItem);

        return savedItem;
    }

    // ==========================
    // Get All Lost Reports
    // ==========================

    @GetMapping
    public List<LostItem> getAllLostItems() {
        return lostRepo.findAll();
    }

    // ==========================
    // Get My Reports
    // ==========================

    @GetMapping("/my-reports/{email}")
    public List<LostItem> getMyReports(
            @PathVariable String email
    ) {
        return lostRepo.findByEmail(email);
    }

    // ==========================
    // Live Bag Tag Suggestions
    // ==========================

    @GetMapping("/search-bag")
    public List<String> searchBagTag(
            @RequestParam String keyword
    ) {

        if (keyword.length() < 3) {
            return List.of();
        }

        return lostRepo
                .findTop5ByBagTagNumberStartingWithIgnoreCase(keyword)
                .stream()
                .map(LostItem::getBagTagNumber)
                .distinct()
                .collect(Collectors.toList());

    }

    // ==========================
    // Live Ticket Suggestions
    // ==========================

    @GetMapping("/search-ticket")
    public List<String> searchTicket(
            @RequestParam String keyword
    ) {

        if (keyword.length() < 3) {
            return List.of();
        }

        return lostRepo
                .findTop5ByTicketNumberStartingWithIgnoreCase(keyword)
                .stream()
                .map(LostItem::getTicketNumber)
                .distinct()
                .collect(Collectors.toList());

    }

    // ==========================
    // Check Duplicate Bag Tag
    // ==========================

    @GetMapping("/check-bag")
    public boolean checkBagTag(
            @RequestParam String bagTag
    ) {

        return lostRepo.existsByBagTagNumber(
                bagTag.trim().toUpperCase()
        );

    }

    // ==========================
    // Check Duplicate Ticket
    // ==========================

    @GetMapping("/check-ticket")
    public boolean checkTicket(
            @RequestParam String ticket
    ) {

        return lostRepo.existsByTicketNumber(
                ticket.trim().toUpperCase()
        );

    }

}