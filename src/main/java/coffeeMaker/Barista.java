package coffeeMaker;

public class Barista extends Coffeemaker {

    public Barista(String name) {
        this.name = name;
    }

    void makeCoffee() {
        System.out.println("I am making coffee for " + name + "!");
    }
}
