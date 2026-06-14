package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/found-items")
@CrossOrigin
public class FoundItemController {

    @Autowired
    private FoundItemRepository foundRepo;

    @Autowired
    private MatchingService matchingService;

    @PostMapping
    public FoundItem createFoundItem(@RequestBody FoundItem foundItem) {
        foundItem.setBagTagNumber(foundItem.getBagTagNumber().trim().toUpperCase());
        foundItem.setTicketNumber(foundItem.getTicketNumber().trim().toUpperCase());

        FoundItem saved = foundRepo.save(foundItem);

        matchingService.matchFoundItem(saved);

        return saved;
    }

    @GetMapping
    public List<FoundItem> getAllFoundItems() {
        return foundRepo.findAll();
    }
}