package payment;

import client.Client;

public class CardPayment implements Payment {
    @Override
    public void pay(Client client, int price) {
        System.out.println("The customer paid by card");
    }
}
