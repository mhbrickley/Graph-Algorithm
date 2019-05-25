public class Edge {
    private int weight;
    private Node neighbor;

    //constructor
    public Edge(int weight, Node neighbor) {
        this.weight = weight;
        this.neighbor = neighbor;
    }

    //getters and setters
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node getNeighbor() {
        return neighbor;
    }

    public void setNeighbour(Node neighbor) {
        this.neighbor = neighbor;
    }
}
