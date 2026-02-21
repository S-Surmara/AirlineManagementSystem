package org.example.Entity;

import org.example.Enums.SeatLocation;
import org.example.Enums.SeatType;

import java.util.UUID;

public class Seat {
    String seatId;
    double seatPrice;
    SeatType seatType;
    SeatLocation seatLocation;
    boolean isAvailable;
    double baggageCapcity;
    public Seat(double seatPrice,SeatType seatType,SeatLocation seatLocation,boolean isAvailable,double baggageCapcity){
        this.seatId = UUID.randomUUID().toString();
        this.seatPrice = seatPrice;
        this.seatLocation = seatLocation;
        this.seatType = seatType;
        this.isAvailable = isAvailable;
        this.baggageCapcity = baggageCapcity;
    }

    public String getSeatId() {
        return seatId;
    }

    public void setSeatId(String seatId) {
        this.seatId = seatId;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public SeatLocation getSeatLocation() {
        return seatLocation;
    }

    public void setSeatLocation(SeatLocation seatLocation) {
        this.seatLocation = seatLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getBaggageCapcity() {
        return baggageCapcity;
    }

    public void setBaggageCapcity(double baggageCapcity) {
        this.baggageCapcity = baggageCapcity;
    }
}
