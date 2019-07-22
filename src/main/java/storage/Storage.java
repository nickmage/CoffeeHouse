package storage;

public class Storage {

    private Integer cup;
    private Integer water;
    private Integer coffee;
    private Integer milk;

    private static Storage ourInstance = new Storage();

    public static Storage getInstance() {
        return ourInstance;
    }

    private Storage() {
        cup = 500;
        water = 700;
        coffee = 800;
        milk = 500;
    }

    public boolean isCup(Integer cup) {
         return this.cup - cup >= 0;
    }

    public void useCup(Integer cup) {
        this.cup -= cup;
    }

    public boolean isWater(Integer water) {
        return this.water - water >= 0;
    }

    public void useWater(Integer water) {
        this.water -= water;
    }

    public boolean isCoffee(Integer coffee) {
        return this.coffee - coffee >= 0;
    }

    public void useCoffee(Integer coffee) {
        this.coffee -= coffee;
    }

    public boolean isMilk(Integer milk) {
         return this.milk - milk >= 0;
    }

    public void useMilk(Integer milk) {
         this.milk -= milk;
    }
}
