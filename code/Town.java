package code;

import java.util.concurrent.TimeUnit;

public class Town {

    int food;
    int materials;
    int energy;
    int prosperity;
    int money;
    int foodAmount;
    int materialsAmount;
    int energyAmount;

    public Town(int food, int materials, int energy, int prosperity, int money) {
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.prosperity = prosperity;
        this.money = money;
    }

    public void RequestFood(int amount, int delay) {
        if (food == 0 || money == 0) {
            return;
        }
        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        foodAmount += amount;

        Wait(delay);

    }

    public void RequestMaterials(int amount, int delay) {
        if (materials == 0 || money == 0) {
            return;
        }

        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        materialsAmount += amount;

        Wait(delay);
    }

    public void RequestEnergy(int amount, int delay) {
        if (energy == 0 || money == 0) {
            return;
        }

        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        energyAmount += amount;

        Wait(delay);

    }

    public void Wait(int delay) {
        // Simulate waiting for the delivery to arrive
        try {
            TimeUnit.SECONDS.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void Build1(int resourcesCost, int price, int increaseProsperity) {
        if (food < resourcesCost || materials < resourcesCost || energy < resourcesCost || money < price) {
            return;
        }

        food -= resourcesCost;
        materials -= resourcesCost;
        energy -= resourcesCost;
        money -= price;
        prosperity += increaseProsperity;
    }

    public void Build2(int resourcesCost, int price, int increaseProsperity) {
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
