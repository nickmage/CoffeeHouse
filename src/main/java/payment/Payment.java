package payment;

import client.Client;

public interface Payment {
    void pay(Client client, int price);
}
