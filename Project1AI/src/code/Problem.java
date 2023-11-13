package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.swing.Action;
import code.Constants.*; 

public class Problem {

    State initialState;
    Actions action;
    Set<State> stateSpace;
    List<Actions> operatorList;
    boolean goalTest;
    int pathCost;
    int prosperity;

    public Problem(State initialState, Set<State> stateSpace) {
    	
    	 List<Actions> list = Arrays.asList(Actions.REQUESTENERGY, Actions.REQUESTFOOD, Actions.REQUESTMATERIALS,
    	            Actions.BUILD1, Actions.BUILD2);
    	 
        this.initialState = initialState;
        this.operatorList = list;
        this.stateSpace = stateSpace;
        this.goalTest = false;
        this.pathCost = 0;
        this.prosperity = initialState.prosperity;
 
    }
    public boolean goalTest(int prosperity) {
        if (prosperity >= 100) {
//        	System.out.println("Goal test = true ");
            return true;
        }
//        System.out.println("Goal test = false \n");
        return false;
    }
 
    public String toString() {
        return "Problem{" +
                "initialState=" + initialState +
                ", operators=" + action +
                ", stateSpace=" + stateSpace +
                ", operatorList=" + operatorList +
                ", goalTest=" + goalTest +
                ", pathCost=" + pathCost +
                ", prosperity=" + prosperity +
                '}';
    }

}