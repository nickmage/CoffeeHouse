package utils;

import java.util.Random;

public enum Coffee {

    LATTE(9), ESPRESSO(8), AMERICANO(7);

    private int price;

    Coffee(int price) {
        this.price = price;
    }

    public static Coffee getCoffee(){
        switch (new Random().nextInt(3)) {
            case 0:
                return LATTE;
            case 1:
                return ESPRESSO;
            default:
                return AMERICANO;
        }
    }

    public int getPrice() {
        return price;
    }

}
