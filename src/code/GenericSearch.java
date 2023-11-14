package code;

import java.util.*;

import code.Constants.*;

public abstract class GenericSearch {

    enum Searchfunction {
        EnqueueAtEnd, OrderedInsert, EnqueueAtFront,
        EnqueueAtFrontWithLimit,Greedy1,Greedy2,as1,as2
    }
//	static HashSet<Node> nodesInQueue = new HashSet<Node>();

    public boolean visualize;
//  Queue<Node> expandedNodes;
    Queue<Node> queue;
    static int depth = 0;
    static Queue<Actions> expansionSequence;
    static HashSet<Node> nodesInQueue = new HashSet<Node>();
    static int iterativeDepthLimit = 0;
    static int delay;
    public static Node GeneralSearch(Problem problem, Searchfunction searchFunction, int maxDepth) {

        Node initialStateNode = new Node(problem.initialState, null, null, 0, 0);

        Strategy searchStrategy = getSearchStrategy(searchFunction);
        Queue<Node> nodes = initializeQueue(searchFunction, initialStateNode);

        expansionSequence = new LinkedList<>();
        nodes.add(initialStateNode);
        while (!nodes.isEmpty()) {
            Node frontNode = nodes.poll();
//            System.out.println("frontNode--- "+frontNode);
            expansionSequence.add(frontNode.operator);

            if (problem.goalTest(frontNode.state.prosperity) == true) {
                return frontNode;
            } else {
//                depth += 1;
                ArrayList<Node> expandedNodes = expand(frontNode, searchStrategy);
                for (Node node : expandedNodes) {
                    nodes.add(node);
                }
            }
        }
        return null;
    }

    private static Strategy getSearchStrategy(Searchfunction searchFunction) {
        switch (searchFunction) {
            case EnqueueAtEnd:
                return Strategy.BF;
            case OrderedInsert:
                return Strategy.UC;
            case EnqueueAtFront:
                return Strategy.DF;
            case EnqueueAtFrontWithLimit:
                return Strategy.DF;
            case Greedy1:
                return Strategy.GR1;
            case Greedy2 :
                return Strategy.GR2;
            case as1:
                return Strategy.AS1;
            case as2 :
                return Strategy.AS2;
            default:
                throw new IllegalArgumentException("Invalid search function");
        }
    }




    private static Queue<Node> initializeQueue(Searchfunction searchFunction, Node initialStateNode) {
        switch (searchFunction) {
            case EnqueueAtEnd:
//                return new LinkedList<>(Collections.singletonList(initialStateNode));
            return new LinkedList<>();

            case OrderedInsert:
                return new PriorityQueue<>(Comparator.comparingInt(node -> node.cost));
            case EnqueueAtFront:
                LinkedList<Node> stack = new LinkedList<>();
//                stack.addFirst(initialStateNode);
                return stack;
            case EnqueueAtFrontWithLimit:
                LinkedList<Node> stackWithLimit = new LinkedList<>();
//                stackWithLimit.addFirst(initialStateNode);
                return stackWithLimit;
            case Greedy1:
                return new PriorityQueue<>(Comparator.comparingInt(Node :: getHeuristicOne));
            case Greedy2:
                return new PriorityQueue<>(Comparator.comparingInt(Node :: getHeuristicTwo));
            case as1:
                return new PriorityQueue<>(Comparator.comparingInt( node-> node.getHeuristicOne() + node.cost));
            case as2:
                return new PriorityQueue<>(Comparator.comparingInt(node-> node.getHeuristicTwo() + node.cost));
            default:
                throw new IllegalArgumentException("Invalid search function");
        }

    }
    public static ArrayList<Node> expand(Node parentNode, Strategy strategy) {

        ArrayList<Node> childNodes = new ArrayList<>();

        Node reqFoodNode = Town.RequestFood(parentNode);
        Node reqMaterialsNode = Town.RequestMaterials(parentNode);

        Node reqEnergyNode = Town.RequestEnergy(parentNode);
        Node build1Node = Town.Build1(parentNode);
        Node build2Node = Town.Build2(parentNode);
        Node waitNode = Town.Wait(parentNode);

//        System.out.println("WAIT Node ----  " + waitNode);
        switch (strategy) {
            case BF:
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
//                    System.out.println("requested resource:: " + parentNode.state.requestedResources);
                    childNodes.add(reqMaterialsNode);
                }
                if (reqEnergyNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqEnergyNode);
                }
                if (waitNode != null && parentNode.state.requestedResources != null) {
                    childNodes.add(waitNode);
                }
                break;

            case DF:
                if (waitNode != null && parentNode.state.requestedResources != null) {
                    childNodes.add(waitNode);
                }
                if (reqFoodNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqFoodNode);
                }
                if (reqMaterialsNode != null && parentNode.state.requestedResources == null) {
//                    System.out.println("requested resource:: " + parentNode.state.requestedResources);
                    childNodes.add(reqMaterialsNode);
                }
                if (reqEnergyNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqEnergyNode);
                }

                if (build2Node != null) {
                    childNodes.add(build2Node);
                }
                if (build1Node != null) {
                    childNodes.add(build1Node);
                }

                break;
            case UC:
                if (reqFoodNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqFoodNode);
                }
                if (reqMaterialsNode != null && parentNode.state.requestedResources == null) {
//                    System.out.println("requested resource:: " + parentNode.state.requestedResources);
                    childNodes.add(reqMaterialsNode);
                }
                if (reqEnergyNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqEnergyNode);
                }
                if (waitNode != null && parentNode.state.requestedResources != null ) { //&& parentNode.state.delay==0
                    childNodes.add(waitNode);
                }

                if (build1Node != null) {
                    childNodes.add(build1Node);
                }
                if (build2Node != null) {
                    childNodes.add(build2Node);
                }

            case GR1:
                if (reqFoodNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqFoodNode);
                }
                if (reqMaterialsNode != null && parentNode.state.requestedResources == null) {
//                    System.out.println("requested resource:: " + parentNode.state.requestedResources);
                    childNodes.add(reqMaterialsNode);
                }
                if (reqEnergyNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqEnergyNode);
                }
                if (waitNode != null && parentNode.state.requestedResources != null ) { //&& parentNode.state.delay==0
                    childNodes.add(waitNode);
                }

                if (build1Node != null) {
                    childNodes.add(build1Node);
                }
                if (build2Node != null) {
                    childNodes.add(build2Node);
                }
            default:

                if (reqFoodNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqFoodNode);
                }
                if (reqMaterialsNode != null && parentNode.state.requestedResources == null) {
//                    System.out.println("requested resource:: " + parentNode.state.requestedResources);
                    childNodes.add(reqMaterialsNode);
                }
                if (reqEnergyNode != null && parentNode.state.requestedResources == null) {
                    childNodes.add(reqEnergyNode);
                }
                if (waitNode != null && parentNode.state.requestedResources != null ) { //&& parentNode.state.delay==0
                    childNodes.add(waitNode);
                }

                if (build1Node != null) {
                    childNodes.add(build1Node);
                }
                if (build2Node != null) {
                    childNodes.add(build2Node);
                }

                break;
        }

        removeReduntantNodes(childNodes);
        if (childNodes != null) {
//            System.out.println("Child nodes:");
        }

        return childNodes;
    }

    public static void removeReduntantNodes(ArrayList<Node> nodes) {
        Iterator<Node> iterator = nodes.iterator();
        while (iterator.hasNext()) {
            Node node = iterator.next();
            if (nodesInQueue.contains(node)) {
                System.out.println("CONTAINS");
                iterator.remove();
            } else {
                nodesInQueue.add(node);
            }
        }
    }

    public abstract boolean goalTest(Node node);

}
