package org.example;

import org.example.Entity.*;
import org.example.Enums.*;
import org.example.Stratergy.CardPaymentStratergy;
import org.example.Stratergy.PaymentStratergy;
import org.example.Stratergy.UPIPaymentStratergy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args){
        // ─────────────────────────────────────────────
        // 1. Get Singleton Facade instance
        // ─────────────────────────────────────────────
        AirlineManagementSystem ams = AirlineManagementSystem.getInstance();

        // ─────────────────────────────────────────────
        // 2. Create Seats
        // ─────────────────────────────────────────────
        Seat seat1 = new Seat(3500.0, SeatType.ECONOMY,     SeatLocation.WINDOW, true, 15.0);
        Seat seat2 = new Seat(3500.0, SeatType.ECONOMY,     SeatLocation.AISLE,  true, 15.0);
        Seat seat3 = new Seat(7500.0, SeatType.BUSINESS,    SeatLocation.WINDOW, true, 25.0);
        Seat seat4 = new Seat(12000.0, SeatType.ELITE_BUSINESS, SeatLocation.MIDDLE, true, 30.0);

        // Seatmap: Seat → seat number
        Map<Seat, Integer> seatmap = new LinkedHashMap<>();
        seatmap.put(seat1, 1);
        seatmap.put(seat2, 2);
        seatmap.put(seat3, 3);
        seatmap.put(seat4, 4);

        // ─────────────────────────────────────────────
        // 3. Create Crew members
        // ─────────────────────────────────────────────
        Crew pilot   = new Crew("C001", "Capt. Arjun Rao",  Sex.MALE,   JobTitle.PILOT);
        Crew cabin1  = new Crew("C002", "Priya Sharma",     Sex.FEMALE, JobTitle.CABIN_CREW);

        List<Crew> crewList = new ArrayList<>(Arrays.asList(pilot, cabin1));

        // ─────────────────────────────────────────────
        // 4. Create Flight and register via Facade
        //    (internally goes to FlightManagementService → AirlineRepository)
        // ─────────────────────────────────────────────
        Flight flight1 = new Flight(
                "AI-202",
                "Hyderabad",
                "Delhi",
                LocalDateTime.of(2026, 3, 15, 10, 30),
                AirLines.AIR_INDIA,
                seatmap,
                4,
                crewList
        );

        // Use FlightDetails (Builder pattern) for a second flight
        FlightDetails flight2Details = new FlightDetails.Builder()
                .withSource("Hyderabad")
                .withDestination("Mumbai")
                .withDepartureTime(LocalDateTime.of(2026, 3, 15, 14, 0))
                .withSeatmap(seatmap)
                .withCapacity(4)
                .build();

        Flight flight2 = new Flight(
                "SG-404",
                flight2Details.getSource(),
                flight2Details.getDestination(),
                flight2Details.getDepartureTime(),
                AirLines.INDIGO,
                flight2Details.getSeatmap(),
                flight2Details.getCapacity(),
                new ArrayList<>()
        );

        // Register flights through Facade's FlightManagementService
        ams.getFlightManagementService().addFlight(flight1);
        ams.getFlightManagementService().addFlight(flight2);

        System.out.println("=== Flights registered ===\n");

        // ─────────────────────────────────────────────
        // 5. Create Passengers
        // ─────────────────────────────────────────────
        Passenger passenger1 = new Passenger("P001", "Rahul Verma",  Sex.MALE,   "rahul@email.com", "9876543210");
        Passenger passenger2 = new Passenger("P002", "Sneha Nair",   Sex.FEMALE, "sneha@email.com", "9123456780");

        // ─────────────────────────────────────────────
        // 6. Search for flights
        // ─────────────────────────────────────────────
        System.out.println("=== Searching flights: Hyderabad → Delhi on 2026-03-15 ===");
        List<Flight> results = ams.search("Hyderabad", "Delhi", LocalDate.of(2026, 3, 15));

        if (results.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            results.forEach(f -> System.out.println(
                    "Found: " + f.getFlightName() + " | " + f.getSource()
                            + " → " + f.getDestination()
                            + " | Departure: " + f.getDepartureDateAndTime()
            ));
        }
        System.out.println();

        // ─────────────────────────────────────────────
        // 7. Book a flight — Passenger 1 books seat1 & seat2 (Economy)
        // ─────────────────────────────────────────────
        System.out.println("=== Passenger 1 booking seats 1 & 2 on flight AI-202 ===");

        Booking booking1 = new Booking(
                passenger1.getUserId(),
                Arrays.asList(seat1.getSeatId(), seat2.getSeatId()),
                flight1.getFlightId()
        );

        PaymentStratergy upiPayment = new UPIPaymentStratergy();

        try {
            ams.book(booking1, upiPayment);
            System.out.println("Booking ID: " + booking1.getBookingId());
            System.out.println("Booking Status: " + booking1.getBookingStatus());
        } catch (RuntimeException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        System.out.println();

        // ─────────────────────────────────────────────
        // 8. Try to double-book the same seats (concurrency guard test)
        // ─────────────────────────────────────────────
        System.out.println("=== Passenger 2 tries to book same seats (should FAIL) ===");

        Booking booking2 = new Booking(
                passenger2.getUserId(),
                Arrays.asList(seat1.getSeatId(), seat2.getSeatId()),
                flight1.getFlightId()
        );

        PaymentStratergy cardPayment = new CardPaymentStratergy();

        try {
            ams.book(booking2, cardPayment);
        } catch (RuntimeException e) {
            System.out.println("Expected failure: " + e.getMessage());
            System.out.println("Booking Status: " + booking2.getBookingStatus());
        }
        System.out.println();

        // ─────────────────────────────────────────────
        // 9. Passenger 2 books a different seat (Business)
        // ─────────────────────────────────────────────
        System.out.println("=== Passenger 2 booking seat 3 (Business) on flight AI-202 ===");

        Booking booking3 = new Booking(
                passenger2.getUserId(),
                Collections.singletonList(seat3.getSeatId()),
                flight1.getFlightId()
        );

        try {
            ams.book(booking3, cardPayment);
            System.out.println("Booking ID: " + booking3.getBookingId());
            System.out.println("Booking Status: " + booking3.getBookingStatus());
        } catch (RuntimeException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }
        System.out.println();

        // ─────────────────────────────────────────────
        // 10. Cancel Passenger 1's booking (triggers refund)
        // ─────────────────────────────────────────────
        System.out.println("=== Passenger 1 cancels booking ===");

        try {
            ams.cancelBooking(booking1.getBookingId(), upiPayment);
            System.out.println("Booking Status after cancel: " + booking1.getBookingStatus());
        } catch (RuntimeException e) {
            System.out.println("Cancel failed: " + e.getMessage());
        }
        System.out.println();

        // ─────────────────────────────────────────────
        // 11. Verify seat1 is available again after cancellation
        // ─────────────────────────────────────────────
        System.out.println("=== Seat availability after cancel ===");
        System.out.println("Seat1 available: " + seat1.isAvailable());  // should be true
        System.out.println("Seat3 available: " + seat3.isAvailable());  // should be false (booked by P2)

        // ─────────────────────────────────────────────
        // 12. Modify flight details using FlightDetails Builder
        // ─────────────────────────────────────────────
        System.out.println("\n=== Admin modifies flight AI-202 departure time ===");

        FlightDetails updatedDetails = new FlightDetails.Builder()
                .withSource("Hyderabad")
                .withDestination("Delhi")
                .withDepartureTime(LocalDateTime.of(2026, 3, 15, 12, 0)) // delayed by 1.5 hrs
                .withSeatmap(seatmap)
                .withCapacity(4)
                .build();

        ams.getFlightManagementService().modifyFlightDetails(flight1.getFlightId(), updatedDetails);
        System.out.println("Updated departure: " + flight1.getDepartureDateAndTime());

        // ─────────────────────────────────────────────
        // 13. Add/Remove Crew
        // ─────────────────────────────────────────────
        System.out.println("\n=== Admin adds new crew member to flight ===");

        Crew newCrew = new Crew("C003", "Vikram Singh", Sex.MALE, JobTitle.CABIN_CREW);
        ams.getUserRepository().addUser(newCrew);
        ams.getFlightManagementService().addCrew(flight1.getFlightId(), newCrew.getUserId());
        System.out.println("Crew list size: " + flight1.getCrewList().size()); // should be 3

        System.out.println("\n=== Admin removes original cabin crew ===");
        ams.getFlightManagementService().removeCrew(flight1.getFlightId(), cabin1.getUserId());
        System.out.println("Crew list size after removal: " + flight1.getCrewList().size()); // should be 2
    }
}