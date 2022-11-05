import java.util.ArrayList;

public class Graph {
    /* Data Fields */
    private String name = "";
    private ArrayList<Vertex> verticies = new ArrayList<Vertex>();
    private char[][] matrix;

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
    //Prints the maxtrix representation of the graph
    public void printMatrix() {
        int size = verticies.size();
        int i, j;
        matrix = new char[size + 1][size + 1];
        matrix[0][0] = ' ';
        //creates the "labels" of the matrix
        for (i = 1; i < matrix.length; i++) {
            matrix[i][0] = (char)(i + 48);
            matrix[0][i] = (char)(i + 48);
        }
        
    }
}
