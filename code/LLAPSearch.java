package code;

import java.lang.reflect.Array;
import java.util.*;

import code.Problem.Operators;

public class LLAPSearch extends GenericSearch {

    List<Operators> list = Arrays.asList(Operators.REQUEST_ENERGY, Operators.REQUEST_FOOD, Operators.REQUEST_MATERIALS,
            Operators.BUILD1, Operators.BUILD2);
    Town town;
    State InitialState;

    // solve
    public void solve(String initialState, String strategy, boolean visualize) {

        InitialState = new State(initialState);

        town = new Town(InitialState.getInitialFood(), InitialState.getInitialMaterials(),
                InitialState.getInitialEnergy(), InitialState.getInitialProsperity(), 0,
                InitialState.getUnitPriceFood(), InitialState.getUnitPriceMaterials(),InitialState.getUnitPriceEnergy());


        Problem problem = new Problem(InitialState, list, null, false, null);

        switch (strategy) {
            case "BF":
                breadthFirstSearch(problem);
                break;
            case "DF":
                break;
            case "ID":
                break;
            case "GR1":
                break;
            case "GR2":
                break;
            case "AS1":
                break;
            case "AS2":
                break;
            default:
                System.out.println("Invalid search strategy: " + strategy);
                break;
        }
    }

    public List<Node> expand(Node parentNode) {

        List<Node> childNodes = new ArrayList<>();
        int amount = parentNode.state.amountRequestFood;
        int delay = parentNode.state.delayRequestFood;
        town.RequestFood(amount, delay);
        
        // State newState = new State(town.prosperity, town.food, town.materials, town.energy, InitialState.unitPriceFood, InitialState.unitPriceMaterials, InitialState.unitPriceEnergy, InitialState.amountRequestFood, InitialState.delayRequestFood, InitialState.amountRequestMaterials, InitialState.delayRequestMaterials, InitialState.amountRequestEnergy, InitialState.delayRequestEnergy, double priceBUILD1, int foodUseBUILD1, int materialsUseBUILD1, int energyUseBUILD1, int prosperityBUILD1, double priceBUILD2, int foodUseBUILD2, int materialsUseBUILD2, int energyUseBUILD2, int prosperityBUILD2)

        // // get the results of all actions (operators)
        // Node n1 = new Node(State object, Node parent, Operators operator, int depth,0);

        return null;
    }

}
