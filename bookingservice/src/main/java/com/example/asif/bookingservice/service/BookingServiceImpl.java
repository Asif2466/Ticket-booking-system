package com.example.asif.bookingservice.service;

import com.example.asif.bookingservice.client.InventoryServiceClient;
import com.example.asif.bookingservice.entity.Customer;
import com.example.asif.bookingservice.event.BookingEvent;
import com.example.asif.bookingservice.repository.CustomerRepository;
import com.example.asif.bookingservice.request.BookingRequest;
import com.example.asif.bookingservice.response.BookingResponse;
import com.example.asif.bookingservice.response.InventoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final CustomerRepository customerRepository;

    private final InventoryServiceClient inventoryServiceClient;

    private final KafkaTemplate<String, BookingEvent> kafkaTemplate; //http://localhost:8084/ui/clusters/create-new-cluster

    @Override
    public BookingResponse createBooking(final BookingRequest bookingRequest) {
        // check if customer exists
        final Customer customer = customerRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        // check if event exists
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(bookingRequest.getEventId());
        log.info("Inventory Response: {}", inventoryResponse);
        if (inventoryResponse == null || inventoryResponse.getCapacity() < bookingRequest.getTicketCount()) {
            throw new RuntimeException("Event not found/Not enough capacity");
        }
        // create booking
        final BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventoryResponse);
        // send booking event to Kafka
        kafkaTemplate.send("booking", bookingEvent);
        log.info("Booking Event sent to Kafka: {}", bookingEvent);

        return BookingResponse.builder()
                .userId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

    private BookingEvent createBookingEvent(final BookingRequest bookingRequest,
                                            final Customer customer,
                                            final InventoryResponse inventoryResponse) {
        return BookingEvent.builder()
                .userId(customer.getId())
                .eventId(bookingRequest.getEventId())
                .ticketCount(bookingRequest.getTicketCount())
                .totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
                .build();
    }
}
