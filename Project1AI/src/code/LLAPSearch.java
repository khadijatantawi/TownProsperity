
package code;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import code.Constants.*;


public class LLAPSearch extends GenericSearch {

    static Town town;
    public static InitialStateParser InitialState;

    public static String solve(String initialState, String strategy, boolean visualize) {

        InitialState = new InitialStateParser(initialState);
        town = new Town(InitialState);
		LLAPSearch LLAP = new LLAPSearch();
		Resource requestedresource=null; 
        State state = new State(InitialState.initialProsperity, InitialState.initialFood, InitialState.initialEnergy,InitialState.initialMaterials, 0 , InitialState,0,requestedresource);
        Problem problem = new Problem(state, null);
        LLAP.visualize=visualize; 
        
        String nodesExpanded; 
        String monetaryCost;
        Node solution=null;
        
        switch (strategy) {
            case "BF":
            	solution = GeneralSearch(problem, Searchfunction.EnqueueAtEnd);break; 
            case "DF":
            	solution = GeneralSearch(problem, Searchfunction.EnqueueAtFront);break; 
            case "ID":
                break;
            case "UC":
            	solution=GeneralSearch(problem, Searchfunction.OrderedInsert);
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
//        System.out.println("SOLUTION22------"+solution);
        String formulatedSolution = LLAP.formulateSolution(solution);
        
        System.out.println(formulatedSolution);
		return formulatedSolution;
       
    }
	public String formulateSolution(Node goalNode) {
//		System.out.println("Goal nodeee"+goalNode); 
		if (goalNode == null) {
//			System.out.println("expansionSequence -- " + expansionSequence);
			return "NOSOLUTION";
		}else {
			if(this.visualize) {
//				System.out.println("Path to goal:");
			}
			return constructPlan(goalNode) + ";" + Integer.toString(goalNode.cost) + ";" + this.expansionSequence.size();
		}
	}
	
	  private static String constructPlan(Node node) {
		    List<String> actions = new ArrayList<>();
		    
		    while (node.parent != null) {
		      actions.add(node.operator.toString());
		      node = node.parent;
		    }
		    Collections.reverse(actions);
		    return String.join(",", actions);
		  }

//	  public String getPlan(Node node) {
//			if(node.parent == null) return "";
//			String parentPlan = getPlan(node.parent);
//			if(this.visualize) {
//				System.out.println(node); // Printing nodes' order to reach goal
//			} 
//			String plan = parentPlan + (parentPlan.equals("")?"":",") + node.operator.name();
//			return plan;
//		}
	  
	@Override
	public boolean goalTest(Node node) {
		return node.state.prosperity >= Constants.GOAL_PROSPERITY_LEVEL;
	}
	
    public static void main(String[] args) {
        LLAPSearch llapSearch = new LLAPSearch();
        String s = "50;22,22,22;50,60,70;30,2;19,1;15,1;300,5,7,3,20;500,8,6,3,40;";
        llapSearch.solve(s, "BF", true);
    }

}