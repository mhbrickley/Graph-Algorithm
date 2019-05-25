import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Invalid amount of arguments");
            return;
        }

        //load graph file
        Graph g = loadGraph(args[0]);

        //load distance file
        Map<String, Integer> distanceToEnd = loadDistanceToEnd(args[1]);

        //user enters starting Node
        String startNode = getStartNode(g);

        //print results
        System.out.println("Algorithm 1\n");

        g.shortestPathA(startNode, distanceToEnd);

        System.out.println("\nAlgorithm 2\n");

        g.shortestPathB(startNode, distanceToEnd);
    }

    //import graph data from text file
    private static Graph loadGraph(String fileName) {
        Graph g = new Graph();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));

            String line = br.readLine();

            String[] nodes = parseNodesNames(line, g);

            while((line = br.readLine()) != null) {
                parseEdges(line, g, nodes);
            }


        } catch (IOException e) {
            System.out.println("Unable to read graph file.");
            System.exit(1);
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while loading the graph file.");
                    System.exit(1);
                }
            }
        }

        return g;
    }

    //parse Nodes from graph file
    private static String[] parseNodesNames(String line, Graph graph) {
        String[] items = line.trim().replaceAll(" +", " ").split(" ");

        for(String name : items) {
            graph.addNode(name);
        }

        return items;
    }

    //parse Edges from graph file
    private static void parseEdges(String line, Graph graph, String[] nodes) {
        String[] items = line.trim().replaceAll(" +", " ").split(" ");

        String currentNodeRow = "";
        for(int i = 0; i < items.length; i++) {
            if(i == 0) {
                currentNodeRow = items[0];
            } else {
                int weight = Integer.parseInt(items[i]);
                if(weight != 0) {
                    graph.addEdge(currentNodeRow, nodes[i - 1], weight);
                }
            }
        }
    }

    //import distance data from text file
    private static Map<String, Integer> loadDistanceToEnd(String fileName) {
        Map<String, Integer> map = new HashMap<>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));

            String line;
            while( (line = br.readLine()) != null) {
                String[] items = line.split(" ");

                map.put(items[0], Integer.parseInt(items[1]));
            }

        } catch (IOException e) {
            System.out.println("Unable to read distances file.");
            System.exit(1);
        } finally {
            if(br != null) {
                 try {
                    br.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while loading the distances file.");
                    System.exit(1);
                }
            }
        }

        return map;
    }

    //take input from user
    private static String getStartNode(Graph graph) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean valid;

        do{
            System.out.print("Insert node: ");
            input = scanner.nextLine();
            valid = true;

            if(input.length() > 1 || !graph.hasNode(input)) {
                valid = false;
                System.out.println("\nInvalid node. Try again...\n");
            }
        } while(!valid);

        return input;
    }
}
