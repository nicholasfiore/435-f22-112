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
        int i, j;
        matrix = new String[size + 1][size + 1];
        matrix[0][0] = " ";
        //creates the "labels" of the matrix
        for (i = 0; i < matrix.length - 1; i++) {
            matrix[i + 1][0] = verticies.get(i).getId() + "";
            matrix[0][i + 1] = verticies.get(i).getId() + "";
        }

        //initializes the matrix to have no adjacencies, marked with a period
        for (i = 1; i < matrix.length; i++) {
            for (j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = ".";
            }
        }
        
        //adds all adjacencies to the matrix, marked by a 1
        for (i = 0; i < verticies.size(); i++) {
            Vertex vert = verticies.get(i);
            for (j = 0; j < vert.getNeighborSize(); j++) {
                Vertex neighbor = vert.getNeighbor(j);
                int vertIndex = Search.linearSearchReturnIndex(verticies, vert.getId());
                int neighborIndex = Search.linearSearchReturnIndex(verticies, neighbor.getId());

                matrix[vertIndex + 1][neighborIndex + 1] = "1";
                matrix[neighborIndex + 1][vertIndex + 1] = "1";
            }
        }

        //prints the matrix
        for (i = 0; i < matrix.length; i++) {
            for (j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
                
            }
            System.out.println();
        }
    }
}
