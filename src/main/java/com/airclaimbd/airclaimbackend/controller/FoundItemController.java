package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/found-items")
@CrossOrigin(origins = "*")
public class FoundItemController {

    @Autowired
    private FoundItemRepository foundRepo;

    @Autowired
    private MatchingService matchingService;

    // ==========================
    // Submit Found Report
    // ==========================

    @PostMapping
    public FoundItem createFoundItem(@RequestBody FoundItem foundItem) {

        String bagTagNumber = foundItem.getBagTagNumber().trim().toUpperCase();
        String ticketNumber = foundItem.getTicketNumber().trim().toUpperCase();

        if (foundRepo.existsByBagTagNumber(bagTagNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Bag Tag Number already exists."
            );
        }

        if (foundRepo.existsByTicketNumber(ticketNumber)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Ticket Number already exists."
            );
        }

        foundItem.setBagTagNumber(bagTagNumber);
        foundItem.setTicketNumber(ticketNumber);

        FoundItem savedItem = foundRepo.save(foundItem);

        matchingService.matchFoundItem(savedItem);

        return savedItem;
    }

    // ==========================
    // Get All Found Reports
    // ==========================

    @GetMapping
    public List<FoundItem> getAllFoundItems() {
        return foundRepo.findAll();
    }

    // ==========================
    // Get My Found Reports
    // ==========================

    @GetMapping("/my-reports/{email}")
    public List<FoundItem> getMyFoundReports(
            @PathVariable String email
    ) {
        return foundRepo.findByEmail(email);
    }

    // ==========================
    // Live Bag Tag Suggestions
    // ==========================

    @GetMapping("/search-bag")
    public List<String> searchBag(
            @RequestParam String keyword
    ) {

        return foundRepo
                .findByBagTagNumberContainingIgnoreCase(
                        keyword.trim().toUpperCase()
                )
                .stream()
                .map(FoundItem::getBagTagNumber)
                .distinct()
                .toList();
    }

    // ==========================
    // Live Ticket Suggestions
    // ==========================

    @GetMapping("/search-ticket")
    public List<String> searchTicket(
            @RequestParam String keyword
    ) {

        return foundRepo
                .findByTicketNumberContainingIgnoreCase(
                        keyword.trim().toUpperCase()
                )
                .stream()
                .map(FoundItem::getTicketNumber)
                .distinct()
                .toList();
    }

    // ==========================
    // Duplicate Bag Tag Check
    // ==========================

    @GetMapping("/check-bag")
    public boolean checkBag(
            @RequestParam String bagTag
    ) {

        return foundRepo.existsByBagTagNumber(
                bagTag.trim().toUpperCase()
        );
    }

    // ==========================
    // Duplicate Ticket Check
    // ==========================

    @GetMapping("/check-ticket")
    public boolean checkTicket(
            @RequestParam String ticketNumber
    ) {

        return foundRepo.existsByTicketNumber(
                ticketNumber.trim().toUpperCase()
        );
    }

}