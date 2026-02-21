package org.example.Entity;

import org.example.Enums.BookingStatus;

import java.util.List;
import java.util.UUID;

public class Booking {

    private String bookingId;
    private String userId;
    // FIXED: Raw List → List<String>
    private List<String> seatIds;
    private String flightId;
    private BookingStatus bookingStatus;

    public Booking(String userId, List<String> seatIds, String flightId) {
        this.bookingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.seatIds = seatIds;
        this.flightId = flightId;
        // FIXED: default status should be PENDING, not passed by caller
        this.bookingStatus = BookingStatus.PENDING;
    }

    public String getBookingId() { return bookingId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public List<String> getSeatIds() { return seatIds; }
    public void setSeatIds(List<String> seatIds) { this.seatIds = seatIds; }
    public String getFlightId() { return flightId; }
    public void setFlightId(String flightId) { this.flightId = flightId; }
    public BookingStatus getBookingStatus() { return bookingStatus; }
    public void setBookingStatus(BookingStatus bookingStatus) { this.bookingStatus = bookingStatus; }
}
