package org.example.Persistance;

import org.example.Entity.Booking;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BookingRepostiry {
    Map<String, Booking> bookingMap;
    public void addBooking(Booking booking){
        bookingMap.put(booking.getBookingId(),booking);
    }

    public Booking getBooking(String bookingId){
        return bookingMap.get(bookingId);
    }

    public List<Booking> getBookingUserId(String userId){
        return bookingMap.values().stream()
                .filter((booking -> booking.getUserId() == userId))
                .collect(Collectors.toList());
    }
}
