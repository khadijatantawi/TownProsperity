package code;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Array;
import java.util.*;

import code.Problem.Operators;

public class LLAPSearch extends GenericSearch {

        List<Operators> list  = Arrays.asList(Operators.REQUEST_ENERGY,Operators.REQUEST_FOOD, Operators.REQUEST_MATERIALS, Operators.BUILD1, Operators.BUILD2);
        public void solve(String initialState, String strategy, boolean visualize) {

        State InitialState = new State(initialState);

        Town town = new Town(InitialState.getInitialFood(), InitialState.getInitialMaterials(),
                InitialState.getInitialEnergy(), InitialState.getInitialProsperity(), 0);

        Problem problem = new Problem(InitialState, list , null, false, null);

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

}
