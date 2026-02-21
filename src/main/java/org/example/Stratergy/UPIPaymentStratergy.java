package org.example.Stratergy;

public class UPIPaymentStratergy implements PaymentStratergy{
    @Override
    public void pay(int amount){
        System.out.println("do payment via UPI banking");
    }
}
