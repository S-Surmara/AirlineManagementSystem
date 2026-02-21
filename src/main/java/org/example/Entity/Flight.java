package org.example.Entity;

import org.example.Enums.AirLines;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Flight {

    private String flightId;
    private String flightName;
    private String source;
    private String destination;
    private LocalDateTime departureDateAndTime;
    private AirLines airLines;

    // FIXED: Raw Map → Map<Seat, Integer> (Seat → seat number), raw List → List<Crew>
    private Map<Seat, Integer> seatmap;
    private int capacity;
    private List<Crew> crewList;

    public Flight(String flightName, String source, String destination,
                  LocalDateTime departureDateAndTime, AirLines airLines,
                  Map<Seat, Integer> seatmap, int capacity, List<Crew> crewList) {
        // FIXED: flightId was set from UUID but constructor also accepted flightId param — simplified
        this.flightId = UUID.randomUUID().toString();
        this.flightName = flightName;
        this.source = source;
        this.destination = destination;
        this.departureDateAndTime = departureDateAndTime;
        this.airLines = airLines;
        // FIXED: initialize to empty collections if null passed
        this.seatmap = seatmap != null ? seatmap : new HashMap<>();
        this.capacity = capacity;
        this.crewList = crewList != null ? crewList : new ArrayList<>();
    }

    public String getFlightId() { return flightId; }
    public String getFlightName() { return flightName; }
    public void setFlightName(String flightName) { this.flightName = flightName; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDateTime getDepartureDateAndTime() { return departureDateAndTime; }
    public void setDepartureDateAndTime(LocalDateTime departureDateAndTime) { this.departureDateAndTime = departureDateAndTime; }
    public AirLines getAirLines() { return airLines; }
    public void setAirLines(AirLines airLines) { this.airLines = airLines; }
    public Map<Seat, Integer> getSeatmap() { return seatmap; }
    public void setSeatmap(Map<Seat, Integer> seatmap) { this.seatmap = seatmap; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public List<Crew> getCrewList() { return crewList; }
    public void setCrewList(List<Crew> crewList) { this.crewList = crewList; }
}
