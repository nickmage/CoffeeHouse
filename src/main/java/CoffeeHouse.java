import client.Client;
import client.VisitorsGenerator;
import storage.Storage;

import java.util.Random;

public class CoffeeHouse {
    private Storage storage = Storage.getInstance();
    Client[] clients = new VisitorsGenerator().getVisitors();

    public static void main(String[] args) {
        /*
        for (Client client : clients) {
            System.out.println(client.getName() + " " + client.isHasCard() + " " + client.getAmountOfCash() + " " +
                    client.getAmountOfMoneyOnTheCard());
        }*/

    }

    void pedal() {
        for (int i = 0; i < clients.length; i++) {
            if (!makeOrder()) {
                System.out.println("BCE");
                break;
            } else {
                payment();

            }
        }
    }

    private boolean makeOrder() {
        switch (new Random().nextInt(3)) {
            case 0:
                return checkIngredients(Coffee.LATTE);
            case 1:
                return checkIngredients(Coffee.ESPRESSO);
            case 2:
                return checkIngredients(Coffee.AMERICANO);
        }
        return false;
    }

    private boolean checkIngredients(Coffee coffee) {
        switch (coffee) {
            case LATTE://2c 2m 1s
                System.out.println("Latte was chosen");
                return storage.isCoffee(2) && storage.isMilk(2) && storage.isCup(1);
            case ESPRESSO://2c 1s
                System.out.println("Espresso was chosen");
                return storage.isCoffee(2) && storage.isCup(1);
            case AMERICANO://2w 2c 1s
                System.out.println("Americano was chosen");
                return storage.isCoffee(2) && storage.isWater(2) && storage.isCup(1);
        }
        return false;
    }

    private void payment() {
    }





    /*
     * order
     * checking ingredients
     * payment
     * create coffee
     * */
}
