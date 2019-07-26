package utils;

import storage.Storage;

public class RecipeBook {
    private Storage storage = Storage.getInstance();

    public boolean hasIngredientsForLatte(){
        return storage.isCoffee(1) && storage.isMilk(2) && storage.isCup(1);
    }
    
    public boolean hasIngredientsForEspresso(){
        return storage.isCoffee(2) && storage.isCup(1);
    }
    
    public boolean hasIngredientsForAmericano(){
        return storage.isCoffee(2) && storage.isWater(2) && storage.isCup(1);
    }

    public void useIngredientsForLatte(){
        storage.useCoffee(2);
        storage.useWater(2);
        storage.useCup(1);
    }

    public void useIngredientsForEspresso(){
        storage.useCoffee(2);
        storage.useWater(2);
        storage.useCup(1);
    }

    public void useIngredientsForAmericano(){
        storage.useCoffee(2);
        storage.useWater(2);
        storage.useCup(1);
    }

}
