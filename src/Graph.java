import java.util.*;

public class Graph {
    //static variable defining the searching node
    private static String FINAL_NODE = "Z";

    //simple map with the node names as key and the node as value
    private Map<String, Node> nodes;

    public Graph() {
        this.nodes = new HashMap<>();
    }

    public void addNode(String name) {
        //avoid inserting the same node twice
        if(!nodes.containsKey(name)) {
            nodes.put(name, new Node(name));
        }
    }

    public void addEdge(String source, String destination, int weight) {
        Node s = nodes.get(source);
        Node d = nodes.get(destination);

        if(s != null && d != null) {
            //avoid inserting an edge twice
            for(Edge e : s.getAdjacents()) {
                if(e.getNeighbor().getName().equals(destination)) {
                    return;
                }
            }

            s.getAdjacents().add(new Edge(weight, d));
        }
    }

    public boolean hasNode(String node) {
        return nodes.containsKey(node);
    }

    private void clearMarks() {
        for(Node n : nodes.values()) {
            n.setVisited(false);
        }
    }

    //Algorithm One
    public int shortestPathA(String start, Map<String, Integer> distanceToEnd) {
        Node s = nodes.get(start);

        clearMarks();

        List<Node> path = new ArrayList<>();
        List<Node> fullPath = new ArrayList<>();

        //get shortest path using the dd(v) heuristic
        int pathLength = shortestPath(s, (n, v) -> {
            //find the distance to the end
            Integer distance = distanceToEnd.get(v);

            if(distance == null) {
                throw new IllegalStateException("Node " + v + " is not part of the distance to end map");
            }

            return distance;
        }, path, fullPath);

        printOutput(pathLength, path, fullPath);

        return pathLength;
    }

    //Algorithm Two
    public int shortestPathB(String start, Map<String, Integer> distanceToEnd) {
        Node s = nodes.get(start);

        clearMarks();

        List<Node> path = new ArrayList<>();
        List<Node> fullPath = new ArrayList<>();

        //get shortest path using the dd(v) + w(n, v) heuristic
        int pathLength = shortestPath(s, (n, v) -> {
            //find distance to the end
            Integer distance = distanceToEnd.get(v);

            if(distance == null) {
                throw new IllegalStateException("Node " + v + " is not part of the distance to end map");
            }

            return distance + getEdgeWeight(n, v);
        }, path, fullPath);

        printOutput(pathLength, path, fullPath);

        return pathLength;
    }

    private int shortestPath(Node n, Heuristic<String, String, Integer> heuristic, List<Node> path, List<Node> fullPath) {
        //add current node to full list
        fullPath.add(n);

        //check if current node is final node
        if(n.getName().equalsIgnoreCase(FINAL_NODE)) {
            path.add(n);
            return 0;
        }

        //visit current node
        n.setVisited(true);

        //get all neighborNodes of current node
        List<Node> neighborNodes = new ArrayList<>();
        for(Edge e : n.getAdjacents()) {
            Node neighbor = e.getNeighbor();

            //get neighbor distance to end
            int distance = heuristic.apply(n.getName(), neighbor.getName());

            //avoid adding neighbors already visited
            if(!neighbor.isVisited()) {
                neighbor.setDistance(distance);
                neighborNodes.add(neighbor);
            }
        }

        //sort neighborNodes according to distance
        neighborNodes.sort(Comparator.comparingInt(Node::getDistance));

        //iterate sorted neighbors and go to minimum
        for(Node neighbor : neighborNodes) {
            int accumWeights = shortestPath(neighbor, heuristic, path, fullPath);

            //if accumWeight < 0 then it's backtracking, iterate to next node
            if(accumWeights >= 0) {
                //if no backtracking, add node to path
                path.add(n);

                //add current node weight to path sum
                return accumWeights + getEdgeWeight(n.getName(), neighbor.getName());
            }

            //add current node to full path since it backtracks
            fullPath.add(n);
        }

        //if no neighbors visited backtrack
        return -1;
    }

    //find the weight of the edge between start and end
    private int getEdgeWeight(String start, String end) {
        Node n = nodes.get(start);
        Node v = nodes.get(end);

        if(n == null || v == null) {
            throw new IllegalStateException("Node " + start + " and node " + end + " are not part of the graph");
        }

        for(Edge e : n.getAdjacents()) {
            if(e.getNeighbor().getName().equalsIgnoreCase(end)) {
                return e.getWeight();
            }
        }

        throw new IllegalStateException("Node " + start + " and node " + end + " are not adjacent");
    }

    //print output
    //NOTE: path is in reverse order
    private void printOutput(int pathLength, List<Node> path, List<Node> fullPath) {
        System.out.print("Sequence of all nodes: ");

        for(int i = 0; i < fullPath.size(); i++) {
            System.out.print(fullPath.get(i));
            if(i < fullPath.size() - 1) {
                System.out.print(" -> ");
            }
        }

        System.out.print("\nShortest path: ");

        for(int i = path.size() - 1; i >= 0; i--) {
            System.out.print(path.get(i));
            if(i > 0) {
                System.out.print(" -> ");
            }
        }

        System.out.println("\nShortest path length: " + pathLength);
    }
}
