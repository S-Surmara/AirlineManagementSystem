package org.example;

import org.example.Entity.Booking;
import org.example.Entity.Flight;
import org.example.Persistance.AirlineRepositry;
import org.example.Persistance.BookingRepostiry;
import org.example.Persistance.UserRepositry;
import org.example.Services.BookingService;
import org.example.Services.FlightManagementService;
import org.example.Services.SearchService;
import org.example.Stratergy.PaymentStratergy;

import java.time.LocalDate;
import java.util.List;

public class AirlineManagementSystem {

    // FIXED: was 'public static' — must be private volatile for double-checked locking
    private static volatile AirlineManagementSystem instance;

    private final SearchService searchService;
    private final BookingService bookingService;
    private final FlightManagementService flightManagementService;

    // FIXED: Constructor was empty — now wires all services (Facade pattern)
    private AirlineManagementSystem() {
        AirlineRepositry airlineRepo = new AirlineRepositry();
        BookingRepostiry bookingRepo = new BookingRepostiry();
        UserRepositry userRepo = new UserRepositry();
        this.searchService = new SearchService(airlineRepo);
        this.bookingService = new BookingService(bookingRepo, airlineRepo);
        this.flightManagementService = new FlightManagementService(airlineRepo, userRepo);
    }

    // FIXED: was synchronized on method — replaced with double-checked locking (better performance)
    public static AirlineManagementSystem getInstance() {
        if (instance == null) {
            synchronized (AirlineManagementSystem.class) {
                if (instance == null) {
                    instance = new AirlineManagementSystem();
                }
            }
        }
        return instance;
    }

    // ADDED: Facade methods delegating to services
    public List<Flight> search(String source, String destination, LocalDate date) {
        return searchService.search(source, destination, date);
    }

    public void book(Booking booking, PaymentStratergy paymentStrategy) {
        bookingService.book(booking, paymentStrategy);
    }

    public void cancelBooking(String bookingId, PaymentStratergy paymentStrategy) {
        bookingService.cancelBooking(bookingId, paymentStrategy);
    }
}
