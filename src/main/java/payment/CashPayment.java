package payment;

import client.Client;

public class CashPayment implements Payment {
    @Override
    public void pay(Client client, int price) {
        System.out.println("The customer paid by cash");
    }
}
