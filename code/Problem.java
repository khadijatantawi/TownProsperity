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

    private State initialState;
    private Operators operators;
    private Set<State> stateSpace;
    private List<Operators> operatorList;
    private boolean goalTest;
    private Function<Object, Double> pathCost;

    public boolean goalTest(int prosperityLevel) {
        if (prosperityLevel >= 100) {
            return true;
        }
        return false;
    }

    public Problem(State initialState, List<Operators> operators, Set<State> stateSpace, boolean goalTest,
            Function<Object, Double> pathCost) {
        this.initialState = initialState;
        this.operatorList = operators;
        this.stateSpace = stateSpace;
        this.goalTest = goalTest;
        this.pathCost = pathCost;
    }

    // Getters for the problem components
    public State getInitialState() {
        return initialState;
    }

    public Operators getOperators() {
        return operators;
    }

    public Set<State> getStateSpace() {
        return stateSpace;
    }

    public boolean getGoalTest() {
        return goalTest;
    }

    public Function<Object, Double> getPathCost() {
        return pathCost;
    }

    public List<Operators> getActions(State state) {
        List<Operators> actions = new ArrayList<>();

        // Check if RequestFood is applicable
        if (state.getAmountRequestFood() <= 50) {
            actions.add(operators.REQUEST_FOOD);
        }

        // Check if RequestMaterials is applicable
        if (state.getAmountRequestMaterials() <= 50) {
            actions.add(operators.REQUEST_MATERIALS);
        }

        // Check if RequestEnergy is applicable
        if (state.getAmountRequestEnergy() <= 50) {
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
