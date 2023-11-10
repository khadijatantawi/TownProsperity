package code;

import java.util.*;

import code.Problem.Operators;

public class GenericSearch {

  enum Searchfunction {
    EnqueueAtEnd, OrderedInsert, EnqueueAtFront
  }

  int depth = 0;

  public Node GeneralSearch(Problem problem, Searchfunction searchFunction) {

    Node initialStateNode = new Node(problem.initialState, null, null, 0, 0.0);
    Queue<Node> nodes = new LinkedList<>();
    nodes.add(initialStateNode);

    while (!nodes.isEmpty()) {

      Node frontNode = nodes.remove();

      if (problem.goalTest(problem.initialState.prosperity)) {
        //
        return frontNode;
      } else {

        State currentState = frontNode.getState();
        depth += 1;

        List<Node> expandedNodes = expand(frontNode, problem.operators);
        nodes.addAll(expandedNodes);

        switch (searchFunction) {
          case EnqueueAtEnd:
            // nodes.addAll(expandedNodes);
            break;
          case OrderedInsert:
            break;
          case EnqueueAtFront:
            // nodes.addAll(0, expandedNodes);
            break;
        }
      }
    }
    return null;
  }

  static int delay;

  public List<Node> expand(Node parentNode, Operators operators) {

    List<Node> childNodes = new ArrayList<>();

    int amount = parentNode.state.initialState.amountRequestFood;
    int delay = parentNode.state.initialState.delayRequestFood;

    Node reqFoodNode = Town.RequestFood(amount, delay, parentNode);
    Node reqMaterialsNode = Town.RequestMaterials(amount, delay, parentNode);

    Node reqEnergyNode = Town.RequestEnergy(amount, delay, parentNode);

    Node build1Node = Town.Build1(parentNode.state.initialState.foodUseBUILD1,
        parentNode.state.initialState.priceBUILD1, parentNode.state.prosperity, parentNode);
    Node build2Node = Town.Build1(parentNode.state.initialState.foodUseBUILD2,
        parentNode.state.initialState.priceBUILD2, parentNode.state.prosperity, parentNode);

    // Check for null before adding to the list
    if (reqFoodNode != null) {
      childNodes.add(reqFoodNode);
    }

    if (reqMaterialsNode != null) {
      childNodes.add(reqMaterialsNode);
    }
    if (reqEnergyNode != null) {
      childNodes.add(reqEnergyNode);
    }
    if (build1Node != null) {
      childNodes.add(build1Node);
    }
    if (build2Node != null) {
      childNodes.add(build2Node);
    }

    // get the results of all actions (operators)
    // Node n1 = new Node(State object, Node parent, Operators operator, int depth,
    // 0);

    return childNodes;
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
    // Implement logic to calculate the total monetary cost from the root node to
    // the current node
    double cost = 0;
    while (node != null) {
      cost += node.cost;
      node = node.parent;
    }
    return Double.toString(cost);
  }

}
