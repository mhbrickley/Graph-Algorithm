import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    private String name;
    private boolean visited;
    private List<Edge> adjacents;
    private int distance;

    //constructor
    public Node(String name) {
        this.name = name;
        this.visited = false;
        this.adjacents = new ArrayList<>();
        this.distance = 0;
    }

    //getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public List<Edge> getAdjacents() {
        return adjacents;
    }

    public void setAdjacents(List<Edge> adjacents) {
        this.adjacents = adjacents;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return visited == node.visited &&
                Objects.equals(name, node.name) &&
                Objects.equals(adjacents, node.adjacents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, visited, adjacents);
    }

    @Override
    public String toString() {
        return name;
    }
}
