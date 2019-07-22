package payment;

import client.Client;

public class CashPayment implements Payment {
    @Override
    public void pay() {
        System.out.println("The customer paid by cash");
    }
}
