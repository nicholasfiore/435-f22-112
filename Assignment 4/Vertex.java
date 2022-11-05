import java.util.ArrayList;

public class Vertex {
    /* Data Fields */
    private int id;
    private boolean processed = false;
    private ArrayList<Vertex> neighbors = new ArrayList<Vertex>();

    /* Constuctors */
    public Vertex() {
        //default empty constructor
    }

    //constuctor with ID parameter
    public Vertex(int idNum) {
        this.id = idNum;
    }

    /* Accessors/Mutators */
    public int getId() {
        return this.id;
    }

    public boolean wasProcessed() {
        return this.processed;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setProcessed(boolean bool) {
        this.processed = bool;
    }

    /* Functions */
    //adds an adjacent vertex as a neighbor to the neighbors ArrayList
    public void addNeighbor(Vertex neighbor) {
        this.neighbors.add(neighbor);
    }
}
