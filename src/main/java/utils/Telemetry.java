package utils;

public class Telemetry {
    private Integer cupSpent = 0;
    private Integer waterSpent = 0;
    private Integer coffeeSpent = 0;
    private Integer milkSpent = 0;
    private Integer moneyEarned = 0;
    private Integer customers = 0;

    public void setCupSpent(Integer cupSpent) {
        this.cupSpent += cupSpent;
    }

    public void setWaterSpent(Integer waterSpent) {
        this.waterSpent += waterSpent;
    }

    public void setCoffeeSpent(Integer coffeeSpent) {
        this.coffeeSpent += coffeeSpent;
    }

    public void setMilkSpent(Integer milkSpent) {
        this.milkSpent += milkSpent;
    }

    public void setMoneyEarned(Integer moneyEarned) {
        this.moneyEarned += moneyEarned;
    }

    public void setCustomers() {
        this.customers++;
    }

    @Override
    public String toString() {
        return "utils.Telemetry{" +
                "Total cup spent = " + cupSpent +
                ", total water spent = " + waterSpent +
                ", total coffee spent = " + coffeeSpent +
                ", total milk spent = " + milkSpent +
                ", total money earned = " + moneyEarned +
                ", quantity of customers = " + customers +
                '}';
    }
}
