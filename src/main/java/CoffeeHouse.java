import client.Client;
import client.VisitorsGenerator;

public class CoffeeHouse {
    public static void main(String[] args) {
        Client [] clients = new VisitorsGenerator().getVisitors();
        for (Client client : clients) {
            System.out.println(client.getName() + " " + client.isHasCard() + " " + client.getAmountOfCash() + " " +
                    client.getAmountOfMoneyOnTheCard());
        }
    }



}
