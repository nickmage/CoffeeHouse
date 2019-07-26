package client;

import java.util.Random;

public class Client {

    private Random random = new Random();
    private String name;
    private boolean hasCard;
    private int amountOfCash;
    private int amountOfMoneyOnTheCard;

    public Client() {
        name = setName();
        hasCard = setHasCard();
        amountOfCash = setAmountOfCash();
        amountOfMoneyOnTheCard = setAmountOfMoneyOnTheCard();
    }


    private String setName() {
        return new Names().getName();
    }

    private boolean setHasCard() {
        return random.nextInt(7) < 5;
    }

    private int setAmountOfCash() {
        return random.nextInt(21);
    }

    private int setAmountOfMoneyOnTheCard() {
        return (hasCard) ? random.nextInt(31): 0;
    }

    public String getName() {
        return name;
    }

    public boolean hasCard() {
        return hasCard;
    }

    public int getAmountOfCash() {
        return amountOfCash;
    }

    public int getAmountOfMoneyOnTheCard() {
        return amountOfMoneyOnTheCard;
    }
}
