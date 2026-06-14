package com.airclaimbd.airclaimbackend.controller;

import com.airclaimbd.airclaimbackend.entity.LostItem;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/lost-items")
@CrossOrigin
public class LostItemController {

    @Autowired
    private LostItemRepository lostRepo;

    @PostMapping
    public LostItem createLostItem(@RequestBody LostItem lostItem) {
        lostItem.setBagTagNumber(lostItem.getBagTagNumber().trim().toUpperCase());
        lostItem.setTicketNumber(lostItem.getTicketNumber().trim().toUpperCase());

        return lostRepo.save(lostItem);
    }

    @GetMapping
    public List<LostItem> getAllLostItems() {
        return lostRepo.findAll();
    }
}