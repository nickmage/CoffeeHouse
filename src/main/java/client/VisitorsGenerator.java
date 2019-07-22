package client;

import java.util.Random;

public class VisitorsGenerator {

    private Client[] clients;

    public VisitorsGenerator() {
        clients = new Client[new Random().nextInt(100) + 1];
        generateVisitors();
    }

    private void generateVisitors(){
        for (int i = 0; i < clients.length; i++) {
            clients[i] = new Client();
        }
    }

    public Client [] getVisitors(){
        return clients;
    }
}
