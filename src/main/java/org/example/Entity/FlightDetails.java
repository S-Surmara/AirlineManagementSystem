package org.example.Entity;

import java.time.LocalDateTime;
import java.util.Map;

public class FlightDetails {
    String source;
    String destination;
    LocalDateTime departureTime;
    Map<Seat,Integer> seatmap;
    int capacity;

    public FlightDetails(String source, String destination, LocalDateTime departureTime, Map<Seat, Integer> seatmap, int capacity) {
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.seatmap = seatmap;
        this.capacity = capacity;
    }

    public static class Builder{
        String source;
        String destination;
        LocalDateTime departureTime;
        Map<Seat,Integer> seatmap;
        int capacity;

        public Builder withSource(String source){
            this.source = source;
            return this;
        }

        public Builder withDestination(String destination){
            this.destination = destination;
            return this;
        }

        public Builder withDepartureTime(LocalDateTime departureTime){
            this.departureTime = departureTime;
            return this;
        }

        public Builder withSeatmap(Map<Seat,Integer> seatmap){
            this.seatmap = seatmap;
            return this;
        }

        public Builder withCapacity(int capacity){
            this.capacity = capacity;
            return this;
        }

        public FlightDetails build(){
            return new FlightDetails(this.source,this.destination,this.departureTime,this.seatmap,this.capacity);
        }
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public Map<Seat, Integer> getSeatmap() {
        return seatmap;
    }

    public int getCapacity() {
        return capacity;
    }
}
