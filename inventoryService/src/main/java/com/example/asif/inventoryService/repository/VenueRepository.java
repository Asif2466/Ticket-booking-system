package com.example.asif.inventoryService.repository;

import com.example.asif.inventoryService.entity.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
    // Define custom query methods if needed
}
