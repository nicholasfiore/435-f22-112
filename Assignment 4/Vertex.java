import java.util.ArrayList;

public class Vertex {
    /* Data Fields */
    private int id;
    private boolean processed = false;
    private ArrayList<Vertex> neighbors = new ArrayList<Vertex>();

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
    public void addNeighbor(Vertex neighbor) {
        this.neighbors.add(neighbor);
    }

    
}
