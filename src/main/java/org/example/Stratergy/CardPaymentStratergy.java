package org.example.Stratergy;

public class CardPaymentStratergy implements PaymentStratergy{
    @Override
    public void pay(int amount){
        System.out.println("do payment via online Card banking");
    }
}
