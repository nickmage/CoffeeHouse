package coffeeMaker;

public class CoffeeMachine extends Coffeemaker {

    public CoffeeMachine(String name) {
        this.name = name;
    }

    void makeCoffee() {
        System.out.println("The machine is making coffee for " + name + "!");
    }
}
