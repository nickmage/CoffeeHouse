import client.Client;
import coffeeMaker.Barista;
import coffeeMaker.CoffeeMachine;
import coffeeMaker.Coffeemaker;
import payment.CardPayment;
import payment.CashPayment;
import storage.Storage;
import utils.Coffee;
import utils.RecipeBook;
import utils.Telemetry;

import java.util.Random;

class CoffeeHouse {
    private Random random = new Random();
    private Telemetry telemetry = new Telemetry();
    private RecipeBook recipeBook = new RecipeBook();

    void engine() {
        while (true) {
            Client client = new Client();
            System.out.println();
            Coffee coffee = Coffee.getCoffee();
            if (!hasIngredients(coffee)) {
                System.out.println("We cannot make coffee today, CoffeeHouse is closed");
                System.out.println(telemetry.toString());
                break;
            } else {
                System.out.println(client.getName() + " " + (client.hasCard() ? "card" : "cash") + " "
                        + client.getAmountOfCash() + " " + client.getAmountOfMoneyOnTheCard());
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
            case LATTE:
                System.out.println("Latte was chosen");
                return recipeBook.hasIngredientsForLatte();
            case ESPRESSO:
                System.out.println("Espresso was chosen");
                return recipeBook.hasIngredientsForEspresso();
            case AMERICANO:
                System.out.println("Americano was chosen");
                return recipeBook.hasIngredientsForAmericano();
        }
        return false;
    }

    private boolean payment(Client client, Coffee coffee) {
        int price = coffee.getPrice();
        if (client.getAmountOfMoneyOnTheCard() - price < 0 || bankError()) {
            if (client.getAmountOfCash() - price < 0) {
                return false;
            } else {
                new CashPayment().pay(client, price);
                return true;
            }
        } else {
            new CardPayment().pay(client, price);
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
                recipeBook.useIngredientsForLatte();
                break;
            case ESPRESSO://2c 1s
                recipeBook.useIngredientsForEspresso();
                break;
            case AMERICANO:
                recipeBook.useIngredientsForAmericano();
                break;
        }
    }

    private void statistics(Coffee coffee) {
        switch (coffee) {
            case LATTE://2c 2m 1s
                telemetry.setCoffeeSpent(1);
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