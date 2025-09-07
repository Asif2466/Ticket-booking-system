package com.example.asif.inventoryService.repository;

import com.example.asif.inventoryService.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Define custom query methods if needed
    // For example, find events by name, date, etc.
}
