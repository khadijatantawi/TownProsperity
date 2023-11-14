package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import code.Constants.*;

//import code.Problem.Operators;

public class Node {
    State state;
    Node parent; // Parent node in the search tree
    Actions operator;
    int depth;
    int cost; // Cost associated with reaching this node


    int foodDelay;
    int materialsDelay;
    int energyDelay;

    public Node(State object, Node parent, Actions operator, int depth, int cost) {

        this.state = object;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.cost = cost;

        this.foodDelay = 0;
        this.materialsDelay = 0;
        this.energyDelay = 0;

    }

    public State getState() {
        return state;
    }

    public int getHeuristicOne() {
        int maxProsperity = Math.max(state.initialState.prosperityBUILD1, state.initialState.prosperityBUILD2);
        int minPrice = Math.min(state.initialState.priceBUILD1, state.initialState.priceBUILD2);
        int neededProsperity = 100 - this.state.prosperity;
        int factor = neededProsperity / maxProsperity;
        return this.cost + (factor * minPrice);
    }
    public int getHeuristicTwo() {
        int maxProsperity = Math.max(this.state.initialState.prosperityBUILD1, state.initialState.prosperityBUILD2);
        int neededProsperity = 100 - this.state.prosperity;
        int neededFood = state.initialState.foodUseBUILD1;
        int factor = neededProsperity / maxProsperity;
        return ((factor*neededFood)-this.state.food) * state.initialState.unitPriceFood;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node other = (Node) obj;
        return state.equals(other.state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }


    public String toString() {

        return "Node { \n" +
            "\tstate=" + state + "," + "\n\t" +
            "\n\tparent node = " + parent + "\n\t" +
            "\n\toperator = " + operator + ",\t" +
            "depth = " + depth + ",\t" +
            "cost = " + cost +
            "}\n";
    }
}
