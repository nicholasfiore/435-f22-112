import java.util.*;

public class Graph {
    /* Data Fields */
    private String name = "";
    private ArrayList<Vertex> verticies = new ArrayList<Vertex>();
    private String[][] matrix;

    /* Constructors */
    //Default, empty constuctor
    public Graph() {

    }
    
    //constuctor with name parameter
    public Graph(String newName) {
        this.name = newName;
    }

    /* Accesors/Mutators */
    public String getName() {
        return this.name;
    }

    public ArrayList<Vertex> getVerticies() {
        return verticies;
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

    //Performs a depth-first search of the graph
    public void depthFirstSearch(Vertex vert) {
        if (!vert.wasProcessed()) {
            System.out.print(vert.getId() + " ");
            vert.setProcessed(true);
        }
        for (int i = 0; i < vert.getNeighborSize(); i++) {
            Vertex neighbor = vert.getNeighbor(i);
            if (!neighbor.wasProcessed()) {
                depthFirstSearch(neighbor);
            }
        }
    }

    //Prints the maxtrix representation of the graph
    public void printMatrix() {
        int size = verticies.size();
        int i, j, k;
        matrix = new String[size + 1][size + 1];
        matrix[0][0] = " ";
        //creates the "labels" of the matrix
        for (i = 1; i < matrix.length; i++) {
            matrix[verticies.get(i).getId()][0] = i + "";
            matrix[0][verticies.get(i).getId()] = i + "";
        }
        
        //
        for (j = 0; j < verticies.size(); j++) {
            Vertex vert = verticies.get(j);
            for (k = 0; 0 < vert.getNeighborSize(); j++) {
                int id = vert.getNeighbor(k).getId();
                int index = Search.linearSearchReturnIndex(verticies, id);
            }
        }
    }
}
