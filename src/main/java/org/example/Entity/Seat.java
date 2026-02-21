package org.example.Entity;

import org.example.Enums.SeatLocation;
import org.example.Enums.SeatType;

import java.util.UUID;

public class Seat {

    private String seatId;
    private double seatPrice;
    private SeatType seatType;
    private SeatLocation seatLocation;
    private boolean isAvailable;
    // FIXED: typo 'baggageCapcity' → 'baggageCapacity'
    private double baggageCapacity;

    public Seat(double seatPrice, SeatType seatType, SeatLocation seatLocation,
                boolean isAvailable, double baggageCapacity) {
        this.seatId = UUID.randomUUID().toString();
        this.seatPrice = seatPrice;
        this.seatType = seatType;
        this.seatLocation = seatLocation;
        this.isAvailable = isAvailable;
        this.baggageCapacity = baggageCapacity;
    }

    public String getSeatId() { return seatId; }
    public double getSeatPrice() { return seatPrice; }
    public void setSeatPrice(double seatPrice) { this.seatPrice = seatPrice; }
    public SeatType getSeatType() { return seatType; }
    public void setSeatType(SeatType seatType) { this.seatType = seatType; }
    public SeatLocation getSeatLocation() { return seatLocation; }
    public void setSeatLocation(SeatLocation seatLocation) { this.seatLocation = seatLocation; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public double getBaggageCapacity() { return baggageCapacity; }
    public void setBaggageCapacity(double baggageCapacity) { this.baggageCapacity = baggageCapacity; }
}
