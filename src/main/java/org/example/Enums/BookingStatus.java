package org.example.Enums;

public enum BookingStatus {
    PENDING,
    SUCCESS,
    CANCELLED,
    // ADDED: needed when payment fails or seats unavailable
    FAILED
}
