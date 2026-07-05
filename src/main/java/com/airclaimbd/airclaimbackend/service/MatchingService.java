package com.airclaimbd.airclaimbackend.service;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import com.airclaimbd.airclaimbackend.entity.LostItem;
import com.airclaimbd.airclaimbackend.repository.FoundItemRepository;
import com.airclaimbd.airclaimbackend.repository.LostItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchingService {

    @Autowired
    private LostItemRepository lostRepository;

    @Autowired
    private FoundItemRepository foundRepository;

    // Called when a Found Item is submitted
    public void matchFoundItem(FoundItem foundItem) {

        Optional<LostItem> lost = lostRepository.findByBagTagNumberAndTicketNumber(
                foundItem.getBagTagNumber(),
                foundItem.getTicketNumber()
        );

        if (lost.isPresent()) {

            LostItem lostItem = lost.get();

            lostItem.setStatus("Matched");
            foundItem.setStatus("Matched");

            lostRepository.save(lostItem);
            foundRepository.save(foundItem);
        }
    }

    // Called when a Lost Item is submitted
    public void matchLostItem(LostItem lostItem) {

        Optional<FoundItem> found = foundRepository.findByBagTagNumberAndTicketNumber(
                lostItem.getBagTagNumber(),
                lostItem.getTicketNumber()
        );

        if (found.isPresent()) {

            FoundItem foundItem = found.get();

            lostItem.setStatus("Matched");
            foundItem.setStatus("Matched");

            lostRepository.save(lostItem);
            foundRepository.save(foundItem);
        }
    }
}