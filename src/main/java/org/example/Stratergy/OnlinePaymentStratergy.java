package org.example.Stratergy;

public class OnlinePaymentStratergy implements PaymentStratergy{
    @Override
    public void pay(int amount){
        System.out.println("do payment via online net banking");
    }
}
