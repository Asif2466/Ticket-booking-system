package com.example.asif.bookingservice.controller;

import com.example.asif.bookingservice.request.BookingRequest;
import com.example.asif.bookingservice.response.BookingResponse;
import com.example.asif.bookingservice.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/booking")
    public ResponseEntity<BookingResponse> bookEvent(@RequestBody BookingRequest bookingRequest) {
        // This method should handle booking an event.
        // Implementation would go here, but is not provided in the original code.
        return ResponseEntity.ok(bookingService.createBooking(bookingRequest));
    }

}
