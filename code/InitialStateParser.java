package code;
public class InitialStateParser {
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

    public InitialStateParser(String initialStateString) {
        // Implement logic to parse the initial state string in the constructor
        String[] parts = initialStateString.split(";");
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

}
