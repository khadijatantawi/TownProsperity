package code;

import code.Problem.Operators;

public class Node {
    State state; // The state of the problem
    Node parent; // Parent node in the search tree
    Operators operator;
    int depth;
    double cost; // Cost associated with reaching this node

    public Node(State object, Node parent, Operators operator, int depth, double cost) {
        this.state = object;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.cost = cost;
    }
    public State getState(){
        return state ; 
    }
}
