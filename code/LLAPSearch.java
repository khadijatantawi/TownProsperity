package code;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

import code.Problem.Operators;

public class LLAPSearch extends GenericSearch {

    List<Operators> list = Arrays.asList(Operators.REQUEST_ENERGY, Operators.REQUEST_FOOD, Operators.REQUEST_MATERIALS,
            Operators.BUILD1, Operators.BUILD2);
    Town town;
    // State InitialState;
    public InitialStateParser InitialState;

    // solve
    public void solve(String initialState, String strategy, boolean visualize) {

        // InitialState = new State(initialState);
        InitialState = new InitialStateParser(initialState);
        State state = new State(InitialState.initialProsperity, InitialState.initialFood, InitialState.initialEnergy,
                InitialState.initialMaterials, 0, InitialState);

        town = new Town(InitialState.initialFood, InitialState.initialMaterials,
                InitialState.initialEnergy, InitialState.initialProsperity, 0,
                InitialState.unitPriceFood, InitialState.unitPriceMaterials,
                InitialState.unitPriceEnergy, InitialState);

        Problem problem = new Problem(state, list, null, false, 0);

        switch (strategy) {
            case "BF":
                GeneralSearch(problem, Searchfunction.EnqueueAtEnd);
                break;
            case "DF":
                GeneralSearch(problem, Searchfunction.EnqueueAtFront);
                break;
            case "ID":
                break;
            case "UC":
                GeneralSearch(problem, Searchfunction.OrderedInsert);
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

    public static void main(String[] args) {
        LLAPSearch llapSearch = new LLAPSearch();
        String s = "50;22,22,22;50,60,70;30,2;19,1;15,1;300,5,7,3,20;500,8,6,3,40;";
        llapSearch.solve(s, "BF", false);
    }

}
