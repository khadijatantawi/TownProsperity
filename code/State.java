package code;

public class State {
    int initialProsperity;

    int initialFood;
    int initialMaterials;
    int initialEnergy;

    int unitPriceFood;
    int unitPriceMaterials;
    int unitPriceEnergy;

    int amountRequestFood;
    int delayRequestFood;

    int amountRequestMaterials;
    int delayRequestMaterials;

    int amountRequestEnergy;
    int delayRequestEnergy;

    int priceBUILD1;
    int foodUseBUILD1;
    int materialsUseBUILD1;
    int energyUseBUILD1;
    int prosperityBUILD1;

    int priceBUILD2;
    int foodUseBUILD2;
    int materialsUseBUILD2;
    int energyUseBUILD2;
    int prosperityBUILD2;

    int moneySpent;

    public State(String input) {
        String[] parts = input.split(";");
        String initialProsperityPart = parts[0];

        String[] initialResourcesPart = parts[1].split(",");
        String[] unitPricesPart = parts[2].split(",");
        String[] foodRequestPart = parts[3].split(",");
        String[] materialsRequestPart = parts[4].split(",");
        String[] energyRequestPart = parts[5].split(",");
        String[] build1Part = parts[6].split(",");
        String[] build2Part = parts[7].split(",");

        // Initialize the variables based on the parsed values
        initialProsperity = Integer.parseInt(initialProsperityPart);

        initialFood = Integer.parseInt(initialResourcesPart[0]);
        initialMaterials = Integer.parseInt(initialResourcesPart[1]);
        initialEnergy = Integer.parseInt(initialResourcesPart[2]);

        unitPriceFood = Integer.parseInt(unitPricesPart[0]);
        unitPriceMaterials = Integer.parseInt(unitPricesPart[1]);
        unitPriceEnergy = Integer.parseInt(unitPricesPart[2]);

        amountRequestFood = Integer.parseInt(foodRequestPart[0]);
        delayRequestFood = Integer.parseInt(foodRequestPart[1]);

        amountRequestMaterials = Integer.parseInt(materialsRequestPart[0]);
        delayRequestMaterials = Integer.parseInt(materialsRequestPart[1]);

        amountRequestEnergy = Integer.parseInt(energyRequestPart[0]);
        delayRequestEnergy = Integer.parseInt(energyRequestPart[1]);

        priceBUILD1 = Integer.parseInt(build1Part[0]);
        foodUseBUILD1 = Integer.parseInt(build1Part[1]);
        materialsUseBUILD1 = Integer.parseInt(build1Part[2]);
        energyUseBUILD1 = Integer.parseInt(build1Part[3]);
        prosperityBUILD1 = Integer.parseInt(build1Part[4]);

        priceBUILD2 = Integer.parseInt(build2Part[0]);
        foodUseBUILD2 = Integer.parseInt(build2Part[1]);
        materialsUseBUILD2 = Integer.parseInt(build2Part[2]);
        energyUseBUILD2 = Integer.parseInt(build2Part[3]);
        prosperityBUILD2 = Integer.parseInt(build2Part[4]);
        moneySpent = 0;
    }

    public State(int initialProsperity, int initialFood, int initialMaterials, int initialEnergy,
            int unitPriceFood, int unitPriceMaterials, int unitPriceEnergy,
            int amountRequestFood, int delayRequestFood,
            int amountRequestMaterials, int delayRequestMaterials,
            int amountRequestEnergy, int delayRequestEnergy,
            int priceBUILD1, int foodUseBUILD1, int materialsUseBUILD1, int energyUseBUILD1, int prosperityBUILD1,
            int priceBUILD2, int foodUseBUILD2, int materialsUseBUILD2, int energyUseBUILD2, int prosperityBUILD2,
            int moneySpent) {

        this.initialProsperity = initialProsperity;
        this.initialFood = initialFood;
        this.initialMaterials = initialMaterials;
        this.initialEnergy = initialEnergy;
        this.unitPriceFood = unitPriceFood;
        this.unitPriceMaterials = unitPriceMaterials;
        this.unitPriceEnergy = unitPriceEnergy;
        this.amountRequestFood = amountRequestFood;
        this.delayRequestFood = delayRequestFood;
        this.amountRequestMaterials = amountRequestMaterials;
        this.delayRequestMaterials = delayRequestMaterials;
        this.amountRequestEnergy = amountRequestEnergy;
        this.delayRequestEnergy = delayRequestEnergy;
        this.priceBUILD1 = priceBUILD1;
        this.priceBUILD2 = priceBUILD2;
        this.foodUseBUILD1 = foodUseBUILD1;
        this.foodUseBUILD2 = foodUseBUILD2;
        this.materialsUseBUILD1 = materialsUseBUILD1;
        this.materialsUseBUILD2 = materialsUseBUILD2;
        this.energyUseBUILD1 = energyUseBUILD1;
        this.energyUseBUILD2 = energyUseBUILD2;
        this.prosperityBUILD1 = prosperityBUILD1;
        this.prosperityBUILD2 = prosperityBUILD2;
        this.moneySpent = moneySpent;
    }
    // Getter methods for accessing the state attributes

    public int getInitialProsperity() {
        return initialProsperity;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public int getInitialFood() {
        return initialFood;
    }

    public int getInitialMaterials() {
        return initialMaterials;
    }

    public int getInitialEnergy() {
        return initialEnergy;
    }

    public int getUnitPriceFood() {
        return unitPriceFood;
    }

    public int getUnitPriceMaterials() {
        return unitPriceMaterials;
    }

    public int getUnitPriceEnergy() {
        return unitPriceEnergy;
    }

    public int getAmountRequestFood() {
        return amountRequestFood;
    }

    public int getDelayRequestFood() {
        return delayRequestFood;
    }

    public int getAmountRequestMaterials() {
        return amountRequestMaterials;
    }

    public int getDelayRequestMaterials() {
        return delayRequestMaterials;
    }

    public int getAmountRequestEnergy() {
        return amountRequestEnergy;
    }

    public int getDelayRequestEnergy() {
        return delayRequestEnergy;
    }

    public int getPriceBUILD1() {
        return priceBUILD1;
    }

    public int getFoodUseBUILD1() {
        return foodUseBUILD1;
    }

    public int getMaterialsUseBUILD1() {
        return materialsUseBUILD1;
    }

    public int getEnergyUseBUILD1() {
        return energyUseBUILD1;
    }

    public int getProsperityBUILD1() {
        return prosperityBUILD1;
    }

    public int getPriceBUILD2() {
        return priceBUILD2;
    }

    public int getFoodUseBUILD2() {
        return foodUseBUILD2;
    }

    public int getMaterialsUseBUILD2() {
        return materialsUseBUILD2;
    }

    public int getEnergyUseBUILD2() {
        return energyUseBUILD2;
    }

    public int getProsperityBUILD2() {
        return prosperityBUILD2;
    }

    // public static void main(String[] args) {
    // String input = "50;" +
    // "22,22,22;" +
    // "50,60,70;" +
    // "30,2;19,1;15,1;" +
    // "300,5,7,3,20;" +
    // "500,8,6,3,40;";
    // State state = new State(input);

    // // Access the state attributes using getter methods
    // System.out.println("Initial Prosperity: " + state.getInitialProsperity());
    // System.out.println("Initial Food: " + state.getInitialFood());
    // System.out.println("Unit Price Food: " + state.getUnitPriceFood());
    // System.out.println("Money spent: " + state.getEnergyUseBUILD1());
    // // ... and so on for other attributes
    // }
}
