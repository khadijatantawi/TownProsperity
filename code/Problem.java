package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.swing.Action;

public class Problem {

    public enum Operators {
        REQUEST_FOOD, REQUEST_MATERIALS, REQUEST_ENERGY, WAIT, BUILD1, BUILD2
    }

    State initialState;
    Operators operators;
    Set<State> stateSpace;
    List<Operators> operatorList;
    boolean goalTest;
    int pathCost;
    int prosperity;

    public Problem(State initialState, List<Operators> operators, Set<State> stateSpace, boolean goalTest,
            int pathCost) {

        this.initialState = initialState;
        this.operatorList = operators;
        this.stateSpace = stateSpace;
        this.goalTest = goalTest;
        this.pathCost = pathCost;
        this.prosperity = initialState.prosperity;

    }

    public boolean goalTest(int prosperity) {
        if (prosperity >= 100) {
            return true;
        }
        return false;
    }

    public List<Operators> getActions(State state) {

        List<Operators> actions = new ArrayList<>();

        // Check if RequestFood is applicable
        if (state.food <= 50) {
            actions.add(operators.REQUEST_FOOD);
        }

        // Check if RequestMaterials is applicable
        if (state.materials <= 50) {
            actions.add(operators.REQUEST_MATERIALS);
        }

        // Check if RequestEnergy is applicable
        if (state.energy <= 50) {
            actions.add(operators.REQUEST_ENERGY);
        }

        // Always add the WAIT action
        actions.add(operators.WAIT);
        // Add other actions as needed
        actions.add(operators.BUILD1);
        actions.add(operators.BUILD2);

        return actions;
    }

}
