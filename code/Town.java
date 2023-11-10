package code;

import java.util.concurrent.TimeUnit;

import code.Problem.Operators;

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
    static InitialStateParser initialState;
    static int amountRequestMaterials;
    static int delay;
    

    public Town(int food, int materials, int energy, int prosperity, int money, int unitPriceFood,
            int unitPriceMaterials, int unitPriceEnergy, InitialStateParser initialState) {
        this.food = food;
        this.materials = materials;
        this.energy = energy;
        this.prosperity = prosperity;
        this.money = money;
        this.unitPriceFood = unitPriceFood;
        this.unitPriceMaterials = unitPriceMaterials;
        this.unitPriceEnergy = unitPriceEnergy;
        this.initialState = initialState;
        moneySpent = 0;
    }

    public static Node RequestFood(int amount, int delay, Node parentNode) {

        if (food == 0 || money == 0 || foodAmount >= 50) {
            return null;
        }
        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        foodAmount += amount;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);

        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_FOOD, amount, delay);
        Wait(delay,childNode);

        return childNode;

    }

    public static Node RequestMaterials(int amount, int delay ,  Node parentNode) {
        if (materials == 0 || money == 0) {
            return null;
        }

        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        materialsAmount += amount;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_FOOD, amount, delay);
        Wait(delay,childNode);
        return childNode;
    }

    public static Node RequestEnergy(int amount, int delay, Node parentNode) {
        if (energy == 0 || money == 0) {
            return null;
        }

        food -= 1;
        materials -= 1;
        energy -= 1;
        money -= amount;
        energyAmount += amount;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;
    
        delay = delay ; 

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_FOOD, amount, delay);
        Wait(delay ,childNode);
        return childNode;

    }

    public static Node Wait(int delay , Node parentNode) {

        if (parentNode.operator== Operators.REQUEST_FOOD || parentNode.operator== Operators.REQUEST_ENERGY ||
        parentNode.operator== Operators.REQUEST_MATERIALS || parentNode.operator== Operators.WAIT){
            delay -=1 ; 
        }
        food -= 1;
        materials -= 1;
        energy -= 1;
        moneySpent += unitPriceFood + unitPriceMaterials + unitPriceEnergy;
       return parentNode; 

    }

    public static Node Build1(int resourcesCost, int price, int increaseProsperity, Node parentNode) {
        if (food < resourcesCost || materials < resourcesCost || energy < resourcesCost || money < price) {
            return null ;
        }
        resourcesCost=parentNode.state.initialState.foodUseBUILD1;

        food -= resourcesCost;
        materials -= resourcesCost;
        energy -= resourcesCost;
        money -= price;
        prosperity += increaseProsperity;
        moneySpent= amountRequestMaterials+( parentNode.state.initialState.foodUseBUILD1*unitPriceFood)+(parentNode.state.initialState.materialsUseBUILD1*unitPriceMaterials)+(parentNode.state.initialState.energyUseBUILD1*unitPriceEnergy);

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
      
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_FOOD , parentNode.depth+1 , GenericSearch.delay);
        return childNode ; 

    }

    public static Node Build2(int resourcesCost, int price, int increaseProsperity , Node parentNode) {
        if (food < resourcesCost || materials < resourcesCost || energy < resourcesCost || money < price) {
            return null ;
        }
        resourcesCost=parentNode.state.initialState.foodUseBUILD2;

        food -= resourcesCost;
        materials -= resourcesCost;
        energy -= resourcesCost;
        money -= price;
        prosperity += increaseProsperity;
        moneySpent= amountRequestMaterials+( parentNode.state.initialState.foodUseBUILD2*unitPriceFood)+(parentNode.state.initialState.materialsUseBUILD2*unitPriceMaterials)+(parentNode.state.initialState.energyUseBUILD2*unitPriceEnergy);

        State newChildState = new State(prosperity, food, energy, materials, moneySpent, initialState);
        Node childNode = new Node(newChildState, parentNode, Operators.REQUEST_FOOD, parentNode.depth+1, delay);
        Wait(delay,childNode);
        return childNode;

    }
}
