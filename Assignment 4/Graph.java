import java.util.ArrayList;

public class Graph {
    /* Data Fields */
    private String name = "";
    private ArrayList<Vertex> verticies = new ArrayList<Vertex>();

    /* Accesors/Mutators */
    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    /* Functions */
    //Adds a vertex to the verticies ArrayList
    public void addVertex(Vertex vert) {
        this.verticies.add(vert);
    }
    //Adds an edge between two verticies by adding each vertex to the other's list of neighbors
    public void addEdge(Vertex vert1, Vertex vert2) {
        vert1.addNeighbor(vert2);
        vert2.addNeighbor(vert1);
    }
}
