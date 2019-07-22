package payment;

import client.Client;

public class CardPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("The customer paid by card");
    }
}
