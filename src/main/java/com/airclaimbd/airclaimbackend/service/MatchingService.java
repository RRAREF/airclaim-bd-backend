package com.airclaimbd.airclaimbackend.service;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import com.airclaimbd.airclaimbackend.entity.LostItem;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchingService {

    @Autowired
    private LostItemRepository lostRepo;

    @Autowired
    private FoundItemRepository foundRepo;

    public void matchFoundItem(FoundItem foundItem) {

        String bag = foundItem.getBagTagNumber().trim().toUpperCase();
        String ticket = foundItem.getTicketNumber().trim().toUpperCase();

        List<LostItem> allLost = lostRepo.findAll();

        for (LostItem lost : allLost) {

            String lostBag = lost.getBagTagNumber().trim().toUpperCase();
            String lostTicket = lost.getTicketNumber().trim().toUpperCase();

            if (lostBag.equals(bag) || lostTicket.equals(ticket)) {

                lost.setStatus("Matched");
                foundItem.setStatus("Matched");

                lostRepo.save(lost);
                foundRepo.save(foundItem);

                return;
            }
        }
    }
}