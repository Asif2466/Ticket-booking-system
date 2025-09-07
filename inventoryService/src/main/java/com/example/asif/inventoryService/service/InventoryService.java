package com.example.asif.inventoryService.service;

import com.example.asif.inventoryService.response.EventInventoryResponse;
import com.example.asif.inventoryService.response.VenueInventoryResponse;

import java.util.List;

public interface InventoryService {

    List<EventInventoryResponse> inventoryGetAllEvents();

    VenueInventoryResponse getVenueInformation(Long venueId);

    EventInventoryResponse getEventInformation(Long eventId);

    void updateEventCapacity(Long eventId, Long newCapacity);
}
