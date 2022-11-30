import java.util.*;

/*
 * Graph data structure, using Vertex objects as nodes. This version is for directed graphs
 */

public class Graph {
    /* Data Fields */
    private String name = "";
    private ArrayList<Vertex> verticies = new ArrayList<Vertex>();
    private ArrayList<Edge> edges = new ArrayList<Edge>();
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

    public ArrayList<Edge> getEdges() {
        return edges;
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
    public void addEdge(Vertex vert1, Vertex vert2, int weight) {
        this.edges.add(new Edge(vert1, vert2, weight));
    }

    //resets the "processed" status on all verticies in a graph.
    public void resetProcessing() {
        for (int i = 0; i < verticies.size(); i++) {
            verticies.get(i).setProcessed(false);
        }
    }

    public boolean singleSourceShortestPath(Vertex source) {
        boolean retVal = true;
        int[] dist = new int[this.verticies.size()];
        
        //Step 1: initializing the distances
        for (int i = 0; i < this.getVerticies().size(); i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        int srcIndex = Search.linearSearchReturnIndex(verticies, source.getId()); //finds the index of the source vertex in verticies
        dist[srcIndex] = 0; //initializes the distance from the source vertex to the source vertex as 0


        //Step 2: Relax all edges
        for (int i = 0; i < this.getVerticies().size() - 1; i++) {
            for(int j = 0; j < this.getEdges().size(); j++) {
                //relaxation step
                Edge currEdge = this.edges.get(j);
                Vertex edgeOrigin = currEdge.getOrigin();
                int originIndex = Search.linearSearchReturnIndex(verticies, edgeOrigin.getId());
                Vertex edgeDestination = currEdge.getConnection();
                int destIndex = Search.linearSearchReturnIndex(verticies, edgeDestination.getId());
                int weight = currEdge.getWeight();
                if (dist[originIndex] != Integer.MAX_VALUE && dist[originIndex] + weight < dist[destIndex]) {
                    dist[destIndex] = dist[originIndex] + weight;
                    edgeDestination.setPredecessor(edgeOrigin);
                }
            }
        }

        //Step 3: Test for negative weight cycles
        for (int i = 0; i < this.getEdges().size(); i++) {
            Edge currEdge = this.edges.get(i);
            Vertex edgeOrigin = currEdge.getOrigin();
            int originIndex = Search.linearSearchReturnIndex(verticies, edgeOrigin.getId());
            Vertex edgeDestination = currEdge.getConnection();
            int destIndex = Search.linearSearchReturnIndex(verticies, edgeDestination.getId());
            int weight = currEdge.getWeight();

            if (dist[originIndex] != Integer.MAX_VALUE && dist[originIndex] + weight < dist[destIndex])
                retVal = false;
        }
        
        //Step 4: Printing
        VertStack pathStack = null;
        Vertex currVert = null;
        if (retVal) {
            for (int i = 0; i < this.getVerticies().size(); i++) {
                if (i != srcIndex) {
                    System.out.print(verticies.get(srcIndex).getId() + " --> " + verticies.get(i).getId() + " cost is " + dist[i] + "; ");
                    pathStack = new VertStack();
                    currVert = verticies.get(i);
                    while (currVert.getPredecessor() != null) { //pushes the reverse path into the stack
                        pathStack.push(new VertNode(currVert));
                        currVert = currVert.getPredecessor();
                    }

                    System.out.print("path: " + verticies.get(srcIndex).getId() + " --> ");
                    while (!pathStack.isEmpty()) { //pops the in-order path from the stack and prints with formatting
                        System.out.print(pathStack.pop().getMyVertex().getId());
                        if (!pathStack.isEmpty()) {
                            System.out.print(" --> ");
                        }
                    }
                    System.out.println(); //line break
                }
            }
        } else {
            System.out.println("There was no path found due to a negative loop.");
        }

        System.out.println();

        return retVal;
    }


}
