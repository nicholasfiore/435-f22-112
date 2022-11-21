/*
 * 
 */

public class Edge {
    Vertex origin = null;
    Vertex destination = null;
    int weight;


    public Edge(){
        /* Empty Constructor */
    }

    //full constructor
    public Edge(Vertex org, Vertex dest, int weight) {
        origin = org;
        destination = dest;
        this.weight = weight;
    }

    /* Accessors/Mutators */
    public int getWeight() {
        return this.weight;
    }

    public Vertex getOrigin() {
        return this.origin;
    }

    public Vertex getConnection() {
        return this.destination;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public void setConnection(Vertex destination) {
        this.destination = destination;
    }
}
