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
        cup = 50;
        water = 70;
        coffee = 70;
        milk = 50;
    }

    public Integer getCup() {
        return cup;
    }

    public boolean useCup(Integer cup) {
        if (this.cup - cup <= 0) {
            return false;
        } else {
            this.cup -= cup;
            return true;
        }
    }

    public Integer getWater() {
        return water;
    }

    public boolean useWater(Integer water) {
        if (this.water - water <= 0) {
            return false;
        } else {
            this.water -= water;
            return true;
        }
    }

    public Integer getCoffee() {
        return coffee;
    }

    public boolean useCoffee(Integer coffee) {
        if (this.coffee - coffee <= 0) {
            return false;
        } else {
            this.coffee -= coffee;
            return true;
        }
    }

    public Integer getMilk() {
        return milk;
    }

    public boolean useMilk(Integer milk) {
        if (this.milk - milk <= 0) {
            return false;
        } else {
            this.milk -= milk;
            return true;
        }
    }
}
