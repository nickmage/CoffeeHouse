import client.Client;
import coffeeMaker.Barista;
import coffeeMaker.CoffeeMachine;
import coffeeMaker.Coffeemaker;
import payment.CardPayment;
import payment.CashPayment;
import storage.Storage;
import utils.Coffee;
import utils.Telemetry;

import java.util.Random;

public class CoffeeHouse {
    private Storage storage = Storage.getInstance();
    private Random random = new Random();
    private Telemetry telemetry = new Telemetry();

    public static void main(String[] args) {
        CoffeeHouse house = new CoffeeHouse();
        house.pedal();
    }

    void pedal() {
        while (true) {
            Client client = new Client();
            System.out.println();
            Coffee coffee = Coffee.getCoffee();
            if (!hasIngredients(coffee)) {
                System.out.println("We cannot make coffee today, CoffeeHouse is closed");
                System.out.println(telemetry.toString());
                break;
            } else {
                System.out.println(client.getName() + " " + (client.isHasCard() ? "card" : "cash") + " "
                        + client.getAmountOfCash() + " " + client.getAmountOfMoneyOnTheCard());
                //payment(clients[i], (client) -> {});
                if (!payment(client, coffee)) {
                    System.out.println("The customer doesn't have enough money");
                } else {
                    makeCoffee(coffee, client);
                }
            }
        }
    }

    private boolean hasIngredients(Coffee coffee) {
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

    private boolean payment(Client client, Coffee coffee) {
        int price = coffee.getPrice();
        //System.out.println(price);
        if (client.getAmountOfMoneyOnTheCard() - price < 0 || bankError()) {
            if (client.getAmountOfCash() - price < 0) {
                return false;
            } else {
                client.setAmountOfCash(price);
                new CashPayment().pay();
                //System.out.println(client.getAmountOfCash());
                return true;
            }
        } else {
            client.setAmountOfMoneyOnTheCard(price);
            //System.out.println(client.getAmountOfMoneyOnTheCard());
            new CardPayment().pay();
            return true;
        }
    }

    private boolean bankError() {
        if (random.nextInt(50) < 1) {
            System.out.println("Bank error, please pay by cash");
            return true;
        } else {
            return false;
        }
    }

    private void makeCoffee(Coffee coffee, Client client) {
        Coffeemaker coffeemaker;
        if (random.nextInt(50) < 1) {
            System.out.println("utils.Coffee machine is broken");
            coffeemaker = new Barista(client.getName());
        } else {
            coffeemaker = new CoffeeMachine(client.getName());
        }
        coffeemaker.makeCoffee();
        statistics(coffee);
        switch (coffee) {
            case LATTE://2c 2m 1s
                storage.useCoffee(2);
                storage.useMilk(2);
                storage.useCup(1);
                break;
            case ESPRESSO://2c 1s
                storage.useCoffee(2);
                storage.useCup(1);
                break;
            case AMERICANO://2w 2c 1s
                storage.useCoffee(2);
                storage.useWater(2);
                storage.useCup(1);
                break;
        }
    }

    private void statistics(Coffee coffee) {
        switch (coffee) {
            case LATTE://2c 2m 1s
                telemetry.setCoffeeSpent(2);
                telemetry.setMilkSpent(2);
                telemetry.setCupSpent(1);
                break;
            case ESPRESSO://2c 1s
                telemetry.setCoffeeSpent(2);
                telemetry.setCupSpent(1);
                break;
            case AMERICANO://2w 2c 1s
                telemetry.setCoffeeSpent(2);
                telemetry.setWaterSpent(2);
                telemetry.setCupSpent(1);
                break;
        }
        telemetry.setMoneyEarned(coffee.getPrice());
        telemetry.setCustomers();
    }

}