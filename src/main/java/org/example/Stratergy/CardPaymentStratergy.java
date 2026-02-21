package org.example.Stratergy;

public class CardPaymentStratergy implements PaymentStratergy{
    @Override
    public void pay(double amount) {
        System.out.println("Paid ₹" + amount + " via Card.");
    }

    @Override
    public void refund() {
        System.out.println("Refund initiated to Card.");
    }
}
