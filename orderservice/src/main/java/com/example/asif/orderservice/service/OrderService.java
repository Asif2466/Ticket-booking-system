package com.example.asif.orderservice.service;

import com.example.asif.bookingservice.event.BookingEvent;
import com.example.asif.orderservice.client.InventoryServiceClient;
import com.example.asif.orderservice.entity.Order;
import com.example.asif.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final InventoryServiceClient inventoryServiceClient;

    private final OrderRepository orderRepository;

    @KafkaListener(topics = "booking", groupId = "order-service")
    public void orderEvent(BookingEvent bookingEvent) {
        log.info("Received Order Event: {}", bookingEvent);

        //create order
        Order order = createOrder(bookingEvent);
        orderRepository.saveAndFlush(order);
        log.info("Order created successfully: {}", order);

        //update inventory
        inventoryServiceClient.updateEventCapacity(bookingEvent.getEventId(), bookingEvent.getTicketCount());
        log.info("Inventory updated for eventId: {}", bookingEvent.getEventId());

    }

    private Order createOrder(BookingEvent bookingEvent) {
        return Order.builder()
                .customerId(bookingEvent.getUserId())
                .eventId(bookingEvent.getEventId())
                .ticketCount(bookingEvent.getTicketCount())
                .totalPrice(bookingEvent.getTotalPrice())
                .build();
    }

}
