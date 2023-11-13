package code;

import java.util.*;
import code.Constants.*;

public abstract class GenericSearch {

	enum Searchfunction {
		EnqueueAtEnd, OrderedInsert, EnqueueAtFront
	}
//	static HashSet<Node> nodesInQueue = new HashSet<Node>();

	public boolean visualize;
//  Queue<Node> expandedNodes;

	static int depth = 0;
	static Queue<Actions> expansionSequence;

	public static Node GeneralSearch(Problem problem, Searchfunction searchFunction) {

		Node initialStateNode = new Node(problem.initialState, null, null, 0, 0);
//    StringBuilder result = new StringBuilder();
		int noOfNodes = 0;
		expansionSequence = new LinkedList<>();
		boolean foundGoal = false;

		switch (searchFunction) {

		case EnqueueAtEnd:

			Queue<Node> nodes = new LinkedList<>();
			nodes.add(initialStateNode);

			while (!nodes.isEmpty() && !foundGoal) {

				Node frontNode = nodes.poll();

				expansionSequence.add(frontNode.operator);
				System.out.println("expansionSequence -- " + expansionSequence);
//				System.out.println("front node : " + frontNode);

				noOfNodes += 1;

				if (problem.goalTest(frontNode.state.prosperity) == true) {

					System.out.println("GOAL SATISFIED -- " + frontNode);
//					System.out.println("expansionSequence -- " + expansionSequence);
					foundGoal = true;
					return frontNode;

				} else {

					depth += 1;
					List<Node> expandedNodes = expand(frontNode);
					nodes.addAll(expandedNodes);

				}
			}
			return null;

		case OrderedInsert:
			PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
			priorityQueue.add(initialStateNode);

			while (!priorityQueue.isEmpty()) {

				Node frontNode = priorityQueue.poll();
				noOfNodes += 1;

				if (problem.goalTest(frontNode.state.prosperity)) {

//					System.out.println("GOAL SATISFIED -- " + frontNode);

					return frontNode;

				} else {
					depth += 1;
					List<Node> expandedNodes = expand(frontNode);
					priorityQueue.addAll(expandedNodes);
				}
			}
			break;

		case EnqueueAtFront:
			Stack<Node> stackNodes = new Stack<>();
			stackNodes.push(initialStateNode);

			while (!stackNodes.isEmpty()) {
				Node frontNode = stackNodes.pop();
//				System.out.println("front node : " + frontNode);
				if (problem.goalTest(frontNode.state.prosperity) == true) {
//					System.out.println("GOAL SATISFIED -- " + frontNode);
					return frontNode;
				} else {

					State currentState = frontNode.getState();
					depth += 1;
					List<Node> expandedNodes = expand(frontNode);

					for (Node node : expandedNodes) {
						stackNodes.push(node);
					}
				}
			}
			break;
		}

//		System.out.println("expansionSequence -- " + expansionSequence);
		return null;

	}

	static int delay;

	public static List<Node> expand(Node parentNode) {

		List<Node> childNodes = new ArrayList<>();
		Resource requestedResource;
		int resourceAmount;

		Node reqFoodNode = Town.RequestFood(parentNode);
		Node reqMaterialsNode = Town.RequestMaterials(parentNode);

		Node reqEnergyNode = Town.RequestEnergy(parentNode);
		Node build1Node = Town.Build1(parentNode);
		Node build2Node = Town.Build2(parentNode);
		Node waitNode = Town.Wait(parentNode);

		System.out.println("Build1 Node ----  "+build1Node);
		if (build1Node != null) {
			childNodes.add(build1Node);
		}
		if (build2Node != null) {
			childNodes.add(build2Node);
		}

		if (reqFoodNode != null && parentNode.state.requestedResources == null) {
			childNodes.add(reqFoodNode);
		}
		if (reqMaterialsNode != null && parentNode.state.requestedResources == null) {
			System.out.println("requested resource:: " + parentNode.state.requestedResources);
			childNodes.add(reqMaterialsNode);
		}
		if (reqEnergyNode != null && parentNode.state.requestedResources == null) {
			childNodes.add(reqEnergyNode);
		}
		if (waitNode != null
				&& (parentNode.foodDelay > 0 || parentNode.materialsDelay > 0 || parentNode.energyDelay > 0)
				&& parentNode.state.requestedResources != null) {
			childNodes.add(waitNode);
		}

//		if (childNodes!= null) {
//	    	System.out.println("Child nodes:");
//	    }
//	    int i =1 ; 
//	    for (Node childNode : childNodes) {
//	    	
//	    	System.out.print("node " + i + ":");
//	        System.out.println("\t"+childNode);
//	        System.out.println("\tFOOD DELAY : "+ childNode.foodDelay +"\tENERGY DELAY : "+ childNode.energyDelay+"\tMATERIALS DELAY : "+childNode.materialsDelay+"\n" );
//	
//	        i+=1; 
//	    }
//	    removeReduntantNodes(childNodes);
		return childNodes;
	}

//	public static void removeReduntantNodes(List<Node> nodes) {
//		for (int i=nodes.size()-1;i>=0;i--) {
//			if(nodesInQueue.contains(nodes.get(i))) {
//				nodes.remove(i);
//			}else {
//				nodesInQueue.add(nodes.get(i));
//			}
//		}
//	}
	public abstract boolean goalTest(Node node);

}