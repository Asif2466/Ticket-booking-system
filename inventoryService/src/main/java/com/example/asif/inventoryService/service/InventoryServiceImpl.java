package com.example.asif.inventoryService.service;

import com.example.asif.inventoryService.entity.Event;
import com.example.asif.inventoryService.repository.EventRepository;
import com.example.asif.inventoryService.repository.VenueRepository;
import com.example.asif.inventoryService.response.EventInventoryResponse;
import com.example.asif.inventoryService.response.VenueInventoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl  implements InventoryService{

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;

    @Override
    public List<EventInventoryResponse> inventoryGetAllEvents() {
       final List<Event> events = eventRepository.findAll();
         return events.stream()
                .map(event -> EventInventoryResponse.builder()
                          .event(event.getName())
                          .capacity(event.getAvailableCapacity())
                          .venue(event.getVenue())
                          .ticketPrice(event.getTicketPrice())
                          .build())
                .toList();
    }

    @Override
    public VenueInventoryResponse getVenueInformation(Long venueId) {
        return venueRepository.findById(venueId)
                .map(venue -> VenueInventoryResponse.builder()
                        .venueName(venue.getName())
                        .totalCapacity(venue.getTotalCapacity())
                        .venueAddress(venue.getAddress())
                        .build())
                .orElseThrow(() -> new RuntimeException("Venue not found with id: " + venueId));
    }

    @Override
    public EventInventoryResponse getEventInformation(Long eventId) {
        return eventRepository.findById(eventId)
                .map(event -> EventInventoryResponse.builder()
                        .event(event.getName())
                        .capacity(event.getAvailableCapacity())
                        .venue(event.getVenue())
                        .ticketPrice(event.getTicketPrice())
                        .build())
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
    }

    @Override
    public void updateEventCapacity(Long eventId, Long ticketsBooked) {
        final Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found with id: " + eventId));
        event.setAvailableCapacity(event.getAvailableCapacity() - ticketsBooked);
        eventRepository.save(event);
    }
}
