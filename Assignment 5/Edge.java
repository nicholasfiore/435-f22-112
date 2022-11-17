/*
 * 
 */

public class Edge {
    Vertex origin = null;
    Vertex connection = null;
    int weight;


    public Edge(){
        /* Empty Constructor */
    }

    //full constructor
    public Edge(Vertex org, Vertex conn, int weight) {
        origin = org;
        connection = conn;
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
        return this.connection;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setOrigin(Vertex origin) {
        this.origin = origin;
    }

    public void setConnection(Vertex connection) {
        this.connection = connection;
    }
}
