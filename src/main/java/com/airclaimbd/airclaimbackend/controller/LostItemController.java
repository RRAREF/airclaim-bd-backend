package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.entity.LostItem;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import com.airclaimbd.airclaimbackend.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/lost-items")
@CrossOrigin(origins = "*")
public class LostItemController {

    @Autowired
    private LostItemRepository lostRepo;

    @Autowired
    private MatchingService matchingService;

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

        LostItem lostItem = new LostItem();

        lostItem.setAirportName(airportName);
        lostItem.setPassengerName(passengerName);
        lostItem.setEmail(email);
        lostItem.setPhone(phone);
        lostItem.setItemName(itemName);
        lostItem.setItemDescription(itemDescription);

        lostItem.setBagTagNumber(
                bagTagNumber.trim().toUpperCase()
        );

        lostItem.setTicketNumber(
                ticketNumber.trim().toUpperCase()
        );

        lostItem.setDateLost(dateLost);

        lostItem.setStatus("Pending");

        if (image != null && !image.isEmpty()) {
            lostItem.setImage(image.getBytes());
        }

        LostItem savedItem = lostRepo.save(lostItem);

        // Check whether this lost item matches an existing found item
        matchingService.matchLostItem(savedItem);

        return savedItem;
    }

    @GetMapping
    public List<LostItem> getAllLostItems() {
        return lostRepo.findAll();
    }

    @GetMapping("/my-reports/{email}")
    public List<LostItem> getMyReports(@PathVariable String email) {
        return lostRepo.findByEmail(email);
    }
}