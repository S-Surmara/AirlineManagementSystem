package org.example.Persistance;

import org.example.Entity.Booking;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingRepostiry {

    // FIXED: Raw Map → typed, initialized
    private final Map<String, Booking> bookingMap = new ConcurrentHashMap<>();

    public void addBooking(Booking booking) {
        bookingMap.put(booking.getBookingId(), booking);
    }

    public Booking getBooking(String bookingId) {
        return bookingMap.get(bookingId);
    }

    public List<Booking> getBookingsByUserId(String userId) {
        // FIXED: == → .equals() for String comparison
        return bookingMap.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
