package org.example.Stratergy;

// FIXED: Added refund() — pay and refund are a natural pair in this domain
public interface PaymentStratergy {
    void pay(double amount);
    void refund();
}
