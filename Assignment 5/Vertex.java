import java.util.ArrayList;

/*
 * Vertex objects to be used with a Graph. These give weights to edges in one direction, creating a directed graph.
 */

public class Vertex {
    /* Data Fields */
    private int id;
    private boolean processed = false;

    /* Constuctors */
    public Vertex() {
        //default empty constructor
    }

    //constuctor with ID parameter
    public Vertex(int idNum) {
        this.id = idNum;
        this.processed = false;
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
}
