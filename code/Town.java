package code;

import java.util.concurrent.TimeUnit;

public class Town {

    static int food;
    static int materials;
    static int energy;
    static int prosperity;
    static int money;
    static int foodAmount;
    static int materialsAmount;
    static int energyAmount;
    static int moneySpent;
    static int unitPriceFood;
    static int unitPriceMaterials;
    static int unitPriceEnergy;

    public Town(int food, int materials, int energy, int prosperity, int money, int unitPriceFood,
            int unitPriceMaterials, int unitPriceEnergy) {
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.prosperity = prosperity;
        this.money = money;
        this.unitPriceFood = unitPriceFood;
        this.unitPriceMaterials = unitPriceMaterials;
        this.unitPriceEnergy = unitPriceEnergy;
        moneySpent = 0;
    }

    public static void RequestFood(int amount, int delay) {
        if (food == 0 || money == 0 || foodAmount >= 50) {
            return;
        }
        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        foodAmount += amount;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;

        Wait(delay);

    }

    public static void RequestMaterials(int amount, int delay) {
        if (materials == 0 || money == 0) {
            return;
        }

        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        materialsAmount += amount;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;

        Wait(delay);
    }

    public static void RequestEnergy(int amount, int delay) {
        if (energy == 0 || money == 0) {
            return;
        }

        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        energyAmount += amount;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;

        Wait(delay);

    }

    public static void Wait(int delay) {
        // Simulate waiting for the delivery to arrive
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void Build1(int resourcesCost, int price, int increaseProsperity) {
        if (food < resourcesCost || materials < resourcesCost || energy < resourcesCost || money < price) {
            return;
        }

        food -= resourcesCost;
        materials -= resourcesCost;
        energy -= resourcesCost;
        money -= price;
        prosperity += increaseProsperity;
    }

    public static void Build2(int resourcesCost, int price, int increaseProsperity) {
        if (food < resourcesCost || materials < resourcesCost || energy < resourcesCost || money < price) {
            return;
        }

        food -= resourcesCost;
        materials -= resourcesCost;
        energy -= resourcesCost;
        money -= price;
        prosperity += increaseProsperity;
    }
}
