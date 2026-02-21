package org.example.Services;

import org.example.Entity.Booking;
import org.example.Entity.Flight;
import org.example.Enums.BookingStatus;
import org.example.Persistance.AirlineRepositry;
import org.example.Persistance.BookingRepostiry;

import java.util.List;

public class BookingService {
    BookingRepostiry bookingRepostiry;
    AirlineRepositry airlineRepositry;
    public BookingService(BookingRepostiry bookingRepostiry,AirlineRepositry airlineRepositry){
        this.bookingRepostiry = bookingRepostiry;
    }
    public synchronized void book(Booking booking){
        List<String> seatIds = booking.getSeatIds();
        Flight flight = airlineRepositry.getFlight(booking.getFlightId());
        flight.getSeatmap().keySet().stream()
                .filter(seat -> seatIds.contains(seat.getSeatId()))
                .forEach(seat -> seat.setAvailable(false));
        bookingRepostiry.addBooking(booking);
    }

    public void cancelBooking(String bookingId) {
        Booking booking = bookingRepostiry.getBooking(bookingId);
        booking.setBookingStatus(BookingStatus.CANCEL);

        List<String> seatIds = booking.getSeatIds();
        Flight flight = airlineRepositry.getFlight(booking.getFlightId());

        flight.getSeatmap().keySet().stream()
                .filter(seat -> seatIds.contains(seat.getSeatId()))
                .forEach(seat -> seat.setAvailable(true));
    }

}
