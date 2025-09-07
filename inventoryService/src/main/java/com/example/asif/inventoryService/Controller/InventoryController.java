package com.example.asif.inventoryService.Controller;

import com.example.asif.inventoryService.response.EventInventoryResponse;
import com.example.asif.inventoryService.response.VenueInventoryResponse;
import com.example.asif.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/events")
    public ResponseEntity<List<EventInventoryResponse>> inventoryGetAllEvents() {
        // This method should return a list of all events in the inventory.
        return ResponseEntity.ok(inventoryService.inventoryGetAllEvents());
    }

    @GetMapping("/venue/{venueId}")
    public ResponseEntity<VenueInventoryResponse> inventoryGetEventsByVenue(@PathVariable Long venueId) {
        // This method should return a list of events by venue ID.
        return ResponseEntity.ok(inventoryService.getVenueInformation(venueId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<EventInventoryResponse> inventoryGetEventById(@PathVariable Long eventId) {
        // This method should return an event by its ID.
        return ResponseEntity.ok(inventoryService.getEventInformation(eventId));
    }

    @PatchMapping("/event/{eventId}/capacity/{newCapacity}")
    public ResponseEntity<Void> updateEventCapacity(@PathVariable Long eventId,@PathVariable("newCapacity") Long ticketsBooked) {
        // This method should update the capacity of an event.
        // Implementation would go here, but is not provided in the original code.
        inventoryService.updateEventCapacity(eventId, ticketsBooked);
        return ResponseEntity.ok().build();
    }

}
