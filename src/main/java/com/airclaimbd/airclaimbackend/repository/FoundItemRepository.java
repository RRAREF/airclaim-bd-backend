package com.airclaimbd.airclaimbackend.repository;

import com.airclaimbd.airclaimbackend.entity.FoundItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoundItemRepository extends JpaRepository<FoundItem, Long> {
}