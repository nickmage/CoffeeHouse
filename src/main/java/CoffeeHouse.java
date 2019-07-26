import client.Client;
import coffeeMaker.Barista;
import coffeeMaker.CoffeeMachine;
import coffeeMaker.Coffeemaker;
import payment.CardPayment;
import payment.CashPayment;
import utils.*;

import java.util.Random;

class CoffeeHouse {
    private Random random = new Random();
    private Telemetry telemetry = new Telemetry();
    private RecipeBook recipeBook = new RecipeBook();
    private boolean coffeeMachineInOrder = true;

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
        try {
            bankErrorCheck();
            if (client.getAmountOfMoneyOnTheCard() - price < 0) {
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
        } catch (BankPaymentSystemError e) {
            System.err.println(e.getMessage());
            if (client.getAmountOfCash() - price < 0) {
                return false;
            } else {
                new CashPayment().pay(client, price);
                return true;
            }
        }
    }

    private void bankErrorCheck() throws BankPaymentSystemError {
        if (random.nextInt(50) < 1)
            throw new BankPaymentSystemError("Bank error, please pay by cash");
    }

    private void makeCoffee(Coffee coffee, Client client) {
        Coffeemaker coffeemaker;
        if (coffeeMachineInOrder) {
            try {
                coffeeMachineCheck();
                coffeemaker = new CoffeeMachine();
                coffeemaker.makeCoffee(client.getName());
            } catch (CofeeMachineMalfunctionExceprion e) {
                System.err.println(e.getMessage());
                coffeeMachineInOrder = false;
                coffeemaker = new Barista();
                coffeemaker.makeCoffee(client.getName());
            }
        } else {
            coffeemaker = new Barista();
            coffeemaker.makeCoffee(client.getName());
        }
        statistics(coffee);
        switch (coffee) {
            case LATTE:
                recipeBook.useIngredientsForLatte();
                break;
            case ESPRESSO:
                recipeBook.useIngredientsForEspresso();
                break;
            case AMERICANO:
                recipeBook.useIngredientsForAmericano();
                break;
        }
    }

    private void coffeeMachineCheck() throws CofeeMachineMalfunctionExceprion {
        if (random.nextInt(2) < 1) {
            throw new CofeeMachineMalfunctionExceprion("Coffee machine is broken");
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