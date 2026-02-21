package org.example.Entity;

import org.example.Enums.BookingStatus;

import java.util.List;
import java.util.UUID;

public class Booking {

    String bookingId;
    String userId;
    List<String> seatIds;
    String flightId;
    BookingStatus bookingStatus;

    public Booking(String userId,List<String> seatIds,String flightId,BookingStatus bookingStatus){
        this.bookingId = UUID.randomUUID().toString();
        this.userId = userId;
        this.seatIds = seatIds;
        this.flightId = flightId;
        this.bookingStatus = bookingStatus;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<String> seatIds) {
        this.seatIds = seatIds;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
