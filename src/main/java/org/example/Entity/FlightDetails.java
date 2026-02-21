package org.example.Entity;

import java.time.LocalDateTime;
import java.util.Map;

public class FlightDetails {

    private String source;
    private String destination;
    private LocalDateTime departureTime;
    // FIXED: Raw Map → Map<Seat, Integer>
    private Map<Seat, Integer> seatmap;
    private int capacity;

    private FlightDetails(Builder builder) {
        this.source = builder.source;
        this.destination = builder.destination;
        this.departureTime = builder.departureTime;
        this.seatmap = builder.seatmap;
        this.capacity = builder.capacity;
    }

    public static class Builder {
        private String source;
        private String destination;
        private LocalDateTime departureTime;
        private Map<Seat, Integer> seatmap;
        private int capacity;

        public Builder withSource(String source) { this.source = source; return this; }
        public Builder withDestination(String destination) { this.destination = destination; return this; }
        public Builder withDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; return this; }
        public Builder withSeatmap(Map<Seat, Integer> seatmap) { this.seatmap = seatmap; return this; }
        public Builder withCapacity(int capacity) { this.capacity = capacity; return this; }

        public FlightDetails build() {
            return new FlightDetails(this);
        }
    }

    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public Map<Seat, Integer> getSeatmap() { return seatmap; }
    public int getCapacity() { return capacity; }
}
