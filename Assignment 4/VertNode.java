/*
 * The Node class is used to created linked lists of objects in order to more variably control the size of said list without being locked into an array
 * This is a modified version that wraps a Vertex object pointer within the Node object.
 */
public class VertNode {
    /* Data Fields */
    private Vertex myVertex = null;
    private VertNode myNext = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public VertNode() {}

    //Partial constructor for initializing only the Item within the Node
    public VertNode(Vertex vert) {
        myVertex = vert;
    }

    //Full constuctor for creating a Node object and assigning an character name and next Node object pointer
    public VertNode(Vertex vert, VertNode next) {
        myVertex = vert;
        myNext = next;
    }

    /* Accessors and Mutators */
    //Returns the character String of the Node object
    public Vertex getMyVertex() {
        return myVertex;
    }

    //Returns the pointer for the next Node object in the list
    public VertNode getMyNext() {
        return myNext;
    }

    //Changes the value of the character String of a Node object to a new String
    public void setMyVertex(Vertex myVertex) {
        this.myVertex = myVertex;
    }

    //Changes the pointer of the next pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyNext(VertNode myNext) {
        this.myNext = myNext;
    }

    /* Functions */
    //Checks to see if the Node object has a myNext value != null. Returns boolean depending on result.
    public boolean hasNext() {
        if (this.getMyNext() != null) {
            return true;
        } else {
            return false;
        }
    }

}
