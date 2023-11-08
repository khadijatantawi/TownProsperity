package code;

import java.util.*;

public class GenericSearch {

  enum Searchfunction {
    EnqueueAtEnd, OrderedInsert, EnqueueAtFront
  }

  public void addNode(Node parent, State state, double cost) {
    // Add a new node with the given state, parent, and cost to the search tree
    // You can implement this based on your specific data structure and needs.
  }

  public Node GeneralSearch(Problem problem, Searchfunction searchFunction) {

    Node initialStateNode = new Node(problem.getInitialState(), null, problem.getOperators(), 0, 0.0);
    Queue<Node> nodes = new LinkedList<>();
    nodes.add(initialStateNode); 

    while (!nodes.isEmpty()) {

      Node frontNode = nodes.remove();

      if (problem.getGoalTest()) {
        return frontNode;
      } else {

        State currentState = frontNode.getState();

        switch (searchFunction) {
          case EnqueueAtEnd:

            break;
          case OrderedInsert:
            break;
          case EnqueueAtFront:
            break;

        }
      }
    }
    return null;
  }

  public Node breadthFirstSearch(Problem problem) {
    return GeneralSearch(problem, Searchfunction.EnqueueAtEnd);

  }

  public Node uniformCostSearch(Problem problem) {
    GeneralSearch(problem, Searchfunction.OrderedInsert);
    return null;
  }

  public Node depthFirstSearch(Problem problem) {
    GeneralSearch(problem, Searchfunction.EnqueueAtFront);
    return null;
  }

  public List<Node> expand(Node parentNode) {

    List<Node> childNodes = new ArrayList<>();
    int amount=parentNode.state.amountRequestFood; 
    int delay = parentNode.state.delayRequestFood;
    Town.RequestFood(amount,delay); 

  // get the results of all actions (operators) 
    // Node n1 = new Node(State object, Node parent, Operators operator, int depth, 0); 
  
    return null;
  }

}
