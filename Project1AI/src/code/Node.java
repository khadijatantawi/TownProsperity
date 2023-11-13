package code;

import java.util.Arrays;
import java.util.List;
import code.Constants.* ; 

//import code.Problem.Operators;

public class Node { 
    State state; 
    Node parent; // Parent node in the search tree
    Actions operator;
    int depth;
    int cost; // Cost associated with reaching this node
   
    
    int foodDelay ; 
    int materialsDelay ; 
    int energyDelay ; 

    public Node(State object, Node parent, Actions operator, int depth, int cost) {

        this.state = object;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.cost = cost;
        
        this.foodDelay=0; 
        this.materialsDelay =0;
        this.energyDelay=0 ; 

    }

    public State getState() {
        return state;
    }
  
    
	@Override
	public int hashCode() {
	    return state.hashCode();
	}
	
	
    public String toString() {
    	
    	 return "Node { \n" +
                 "\tstate=" + state +","+"\n\t"+
                 "\n\tparent node = " + parent + "\n\t"+
                 "\n\toperator = " + operator + ",\t" +
                 "depth = " + depth + ",\t" +
                 "cost = " + cost +
                 "}\n";
    }
}