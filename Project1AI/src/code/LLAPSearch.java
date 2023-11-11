package code;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

//import code.Problem.Operators;

public class LLAPSearch extends GenericSearch {

    static List<Operators> list = Arrays.asList(Operators.REQUEST_ENERGY, Operators.REQUEST_FOOD, Operators.REQUEST_MATERIALS,
            Operators.BUILD1, Operators.BUILD2);
    static Town town;
    // State InitialState;
    public static InitialStateParser InitialState;

    // solve
    public static String solve(String initialState, String strategy, boolean visualize) {

        // InitialState = new State(initialState);
        InitialState = new InitialStateParser(initialState);
        int moneySpent =0 ; 
        State state = new State(InitialState.initialProsperity, InitialState.initialFood, InitialState.initialEnergy,
                InitialState.initialMaterials, moneySpent , InitialState);

        town = new Town(InitialState);
        

        Problem problem = new Problem(state, list, null, false, 0);
        String nodesExpanded; 
        String monetaryCost;

//        System.out.println("problem state  " + state);
//        System.out.println(list); 
        System.out.println("Problem : "+ problem); 
        
        
        switch (strategy) {
            case "BF":
                Node BFNode = GeneralSearch(problem, Searchfunction.EnqueueAtEnd);
                String Plan = constructPlan(BFNode);
                nodesExpanded = Integer.toString(BFNode.depth);
                monetaryCost = Integer.toString(BFNode.state.moneySpent);
                return Plan ; 
  
            case "DF":
            	Node DFNode = GeneralSearch(problem, Searchfunction.EnqueueAtFront);
            	System.out.println("solutionNodeOfDFS"+DFNode);
                String DFPlan = constructPlan(DFNode); 
                nodesExpanded = Integer.toString(DFNode.depth);
                monetaryCost = Integer.toString(DFNode.state.moneySpent);
                System.out.println("DF plan "+ DFPlan+ ";" + monetaryCost + ";" + nodesExpanded);
                String result = (DFPlan == null) ? "NOSOLUTION" : DFPlan + "," + monetaryCost + "," + nodesExpanded;
                return result;
                
//                return DFPlan ;
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
        return "NOSOLUTION"; 
    }
    private static String constructPlan(Node node) {
        // Implement logic to construct the plan from the root node to the current node
        List<String> actions = new ArrayList<>();
        while (node.parent != null) {
            actions.add(node.operator.toString());
            node = node.parent; 
        }
        Collections.reverse(actions);
        return String.join(",", actions);
    }
    private static String calculateMonetaryCost(Node node) {
        // Implement logic to calculate the total monetary cost from the root node to the current node
        double cost = 0;
        while (node != null) {
            cost += node.cost;
            node = node.parent;
        }
        return Double.toString(cost);
    }

    public static void main(String[] args) {
        LLAPSearch llapSearch = new LLAPSearch();
        String s = "50;22,22,22;50,60,70;30,2;19,1;15,1;300,5,7,3,20;500,8,6,3,40;";
        llapSearch.solve(s, "DF", false);
    }

}
