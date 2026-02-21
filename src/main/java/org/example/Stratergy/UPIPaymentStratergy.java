package org.example.Stratergy;

public class UPIPaymentStratergy implements PaymentStratergy{
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via UPI.");
    }

    @Override
    public void refund() {
        System.out.println("Refund initiated via UPI.");
    }
}
