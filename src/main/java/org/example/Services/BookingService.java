package org.example.Services;

import org.example.Entity.Booking;
import org.example.Entity.Flight;
import org.example.Entity.Seat;
import org.example.Enums.BookingStatus;
import org.example.Persistance.AirlineRepositry;
import org.example.Persistance.BookingRepostiry;
import org.example.Stratergy.PaymentStratergy;

import java.util.List;

public class BookingService {

    private final BookingRepostiry bookingRepostiry;
    private final AirlineRepositry airlineRepositry;

    public BookingService(BookingRepostiry bookingRepostiry, AirlineRepositry airlineRepositry) {
        this.bookingRepostiry = bookingRepostiry;
        // FIXED: airlineRepositry was injected but never assigned
        this.airlineRepositry = airlineRepositry;
    }

    // FIXED: Added PaymentStrategy param; added availability check; added payment call
    public synchronized void book(Booking booking, PaymentStratergy paymentStratergy) {
        Flight flight = airlineRepositry.getFlight(booking.getFlightId());
        if (flight == null) throw new RuntimeException("Flight not found: " + booking.getFlightId());

        List<String> seatIds = booking.getSeatIds();

        // Step 1: Check all requested seats are available
        boolean allAvailable = flight.getSeatmap().keySet().stream()
                .filter(seat -> seatIds.contains(seat.getSeatId()))
                .allMatch(Seat::isAvailable);

        if (!allAvailable) {
            booking.setBookingStatus(BookingStatus.FAILED);
            throw new RuntimeException("One or more seats are unavailable. Booking failed.");
        }

        // Step 2: Mark seats as unavailable atomically
        flight.getSeatmap().keySet().stream()
                .filter(seat -> seatIds.contains(seat.getSeatId()))
                .forEach(seat -> seat.setAvailable(false));

        // Step 3: Calculate total and process payment
        double totalAmount = flight.getSeatmap().keySet().stream()
                .filter(seat -> seatIds.contains(seat.getSeatId()))
                .mapToDouble(Seat::getSeatPrice)
                .sum();
        paymentStratergy.pay(totalAmount);

        // Step 4: Confirm booking
        booking.setBookingStatus(BookingStatus.SUCCESS);
        bookingRepostiry.addBooking(booking);
        System.out.println("Booking confirmed: " + booking.getBookingId());
    }

    // FIXED: status was CANCEL (wrong enum) → CANCELLED; added refund; added null check
    public synchronized void cancelBooking(String bookingId, PaymentStratergy paymentStratergy) {
        Booking booking = bookingRepostiry.getBooking(bookingId);
        if (booking == null) throw new RuntimeException("Booking not found: " + bookingId);
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            System.out.println("Booking already cancelled.");
            return;
        }

        // Release seats
        Flight flight = airlineRepositry.getFlight(booking.getFlightId());
        List<String> seatIds = booking.getSeatIds();
        flight.getSeatmap().keySet().stream()
                .filter(seat -> seatIds.contains(seat.getSeatId()))
                .forEach(seat -> seat.setAvailable(true));

        booking.setBookingStatus(BookingStatus.CANCELLED);

        // ADDED: trigger refund
        paymentStratergy.refund();
        System.out.println("Booking cancelled and refund initiated for: " + bookingId);
    }
}
