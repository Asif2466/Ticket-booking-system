package com.example.asif.bookingservice.service;

import com.example.asif.bookingservice.request.BookingRequest;
import com.example.asif.bookingservice.response.BookingResponse;

public interface BookingService {
    /**
     * Creates a booking based on the provided request.
     *
     * @param bookingRequest the request containing booking details
     * @return a response containing booking confirmation details
     */
    BookingResponse createBooking(final BookingRequest bookingRequest);

    /**
     * Cancels an existing booking.
     *
     * @param bookingId the ID of the booking to cancel
     * @return a response indicating the result of the cancellation
     */
//    BookingResponse cancelBooking(Long bookingId);

    /**
     * Retrieves details of a specific booking.
     *
     * @param bookingId the ID of the booking to retrieve
     * @return a response containing the booking details
     */
//    BookingResponse getBookingDetails(Long bookingId);
}
