package code;

import java.util.*;

//import code.Problem.Operators;

public class GenericSearch {

  enum Searchfunction {
    EnqueueAtEnd, OrderedInsert, EnqueueAtFront
  }

  static int depth = 0;
//  static List<Node> nodesInQueue = new ArrayList<Node>();
  
  public static String GeneralSearch(Problem problem, Searchfunction searchFunction) {

    Node initialStateNode = new Node(problem.initialState, null, null, 0, 0);
   
    
    StringBuilder result = new StringBuilder();
    int noOfNodes=0 ; 

        switch (searchFunction) {
        
          case EnqueueAtEnd:
        	  
        	  Queue<Node> nodes = new LinkedList<>();
        	  nodes.add(initialStateNode);
        	  while (!nodes.isEmpty()) { 
        		  
        		  Node frontNode = nodes.poll();
        		  noOfNodes += 1 ; 
        		  
        		  if (frontNode.operator != null) {
        			    result.append(frontNode.operator.toString()).append(",");
        			}
        		  
//        		  System.out.println("front node : "+ frontNode);
        		  if (problem.goalTest(frontNode.state.prosperity) == true ) {
        			  
        			  if (result.length() > 0 && result.charAt(result.length() - 1) == ',') {
        			        result.deleteCharAt(result.length() - 1);
        			    }
        		    	System.out.println("GOAL SATISFIED -- " + frontNode);
        		    	 result.append(";").append(frontNode.cost).append(";").append(noOfNodes);
        		    	
        		    	return result.toString();		        
        		  } 
        		  else {
        			  State currentState = frontNode.getState();
        		      depth += 1;
        		      List<Node> expandedNodes = expand(frontNode, problem.operators);
        		      nodes.addAll(expandedNodes);
        		      
        		     
        		   }
        	  }
            break;
            
          case OrderedInsert:
            break;
            
          case EnqueueAtFront:
        	  Stack <Node> stackNodes = new Stack<>();
        		stackNodes.push(initialStateNode);
  
        	  while (!stackNodes.isEmpty()) {
        		  Node frontNode = stackNodes.pop();
        		  System.out.println("front node : "+ frontNode);
        		  if (problem.goalTest(frontNode.state.prosperity) == true ) {
        			  System.out.println("GOAL SATISFIED -- " + frontNode);
        			  
        			  return result.toString();
        		        
        		   }
        		   else {
        		    	
        		        State currentState = frontNode.getState();

        		        depth += 1;

        		        List<Node> expandedNodes = expand(frontNode, problem.operators);
        		        
        		        	for (Node node : expandedNodes) {
        		        		stackNodes.push(node);
        		        	
        		        }
        		   }
        		 
        	  }
      }
    
        return result.toString();
        
  }
//  public static void removeReduntantNodes(List<Node> nodes) {
//		for (int i=nodes.size()-1;i>=0;i--) {
//			if(nodesInQueue.contains(nodes.get(i))) {
//				nodes.remove(i);
//			}else {
//				nodesInQueue.add(nodes.get(i));
//			}
//		}
//	}
  static int delay;

  public static List<Node> expand(Node parentNode, Operators operators) {

    List<Node> childNodes = new ArrayList<>();
    
    Node reqFoodNode = Town.RequestFood(parentNode);
    Node reqMaterialsNode = Town.RequestMaterials(parentNode);
    Node reqEnergyNode = Town.RequestEnergy(parentNode);
    Node build1Node = Town.Build1(parentNode);   
    Node build2Node = Town.Build2(parentNode);
    Node waitNode = Town.Wait(parentNode); 
    
//	System.out.println("town "+Town.food +", "+Town.materials);
//	System.out.println("town "+Town.food +", "+Town.materials);
//	System.out.println("town "+Town.food +", "+Town.materials);
//	System.out.println("reqFoodNode  "+ reqFoodNode);
//	Check for null before adding to the list
    
    if (build1Node != null) {
        childNodes.add(build1Node);
    }    
    if (build2Node != null) {
        childNodes.add(build2Node);
    }
    if (reqFoodNode != null && parentNode.foodDelay== 0 && parentNode.materialsDelay==0 && parentNode.energyDelay==0 ) {
        childNodes.add(reqFoodNode);
    }
//    System.out.print("req Food Node  : " + reqFoodNode);
    if (reqMaterialsNode != null &&  parentNode.foodDelay== 0 && parentNode.materialsDelay==0 && parentNode.energyDelay==0 ) {
        childNodes.add(reqMaterialsNode); 
        }
    if (reqEnergyNode != null &&  parentNode.foodDelay== 0 && parentNode.materialsDelay==0 && parentNode.energyDelay==0) {
        childNodes.add(reqEnergyNode);
    }
    if (waitNode!= null && parentNode.foodDelay> 0 && parentNode.materialsDelay>0 && parentNode.energyDelay>0) {
    	childNodes.add(waitNode);
    }
    
    // get the results of all actions (operators)
    // Node n1 = new Node(State object, Node parent, Operators operator, int depth,
    // 0);
    if (childNodes!= null) {
    	System.out.println("Child nodes:");
    }
    int i =1 ; 
    for (Node childNode : childNodes) {
    	
    	System.out.print("node " + i + ":");
        System.out.println("\t"+childNode);
        System.out.println("\tFOOD DELAY : "+ childNode.foodDelay +"\tENERGY DELAY : "+ childNode.energyDelay+"\tMATERIALS DELAY : "+childNode.materialsDelay+"\n" );

        i+=1; 
    }
//    removeReduntantNodes(childNodes);
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
