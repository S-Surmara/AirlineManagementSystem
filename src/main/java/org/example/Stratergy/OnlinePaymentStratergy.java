package org.example.Stratergy;

public class OnlinePaymentStratergy implements PaymentStratergy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Net Banking.");
    }

    // ADDED: refund implementation
    @Override
    public void refund() {
        System.out.println("Refund initiated via Net Banking.");
    }
}
