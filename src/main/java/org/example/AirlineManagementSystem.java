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

    private static volatile AirlineManagementSystem instance;

    private final SearchService searchService;
    private final BookingService bookingService;
    private final FlightManagementService flightManagementService;

    // FIXED: promoted from local variables to fields so getters can expose them
    private final AirlineRepositry airlineRepo;
    private final BookingRepostiry bookingRepo;
    private final UserRepositry userRepo;

    private AirlineManagementSystem() {
        this.airlineRepo = new AirlineRepositry();
        this.bookingRepo = new BookingRepostiry();
        this.userRepo    = new UserRepositry();

        this.searchService           = new SearchService(airlineRepo);
        this.bookingService          = new BookingService(bookingRepo, airlineRepo);
        this.flightManagementService = new FlightManagementService(airlineRepo, userRepo);
    }

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

    // ── Facade Methods ──────────────────────────────────────────

    public List<Flight> search(String source, String destination, LocalDate date) {
        return searchService.search(source, destination, date);
    }

    public void book(Booking booking, PaymentStratergy paymentStrategy) {
        bookingService.book(booking, paymentStrategy);
    }

    public void cancelBooking(String bookingId, PaymentStratergy paymentStrategy) {
        bookingService.cancelBooking(bookingId, paymentStrategy);
    }

    // ── Service Getters (used by Main for admin operations) ─────

    public FlightManagementService getFlightManagementService() {
        return flightManagementService;
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public BookingService getBookingService() {
        return bookingService;
    }

    // ── Repository Getters (used by Main to register users/flights directly) ──

    public UserRepositry getUserRepository() {
        return userRepo;
    }

    public AirlineRepositry getAirlineRepository() {
        return airlineRepo;
    }

    public BookingRepostiry getBookingRepository() {
        return bookingRepo;
    }
}
