/*
 * The Node class is used to created linked lists of objects in order to more variably control the size of said list without being locked into an array
 */
public class Node {
    /* Data Fields */
    private String myString = "";
    private Node myNext = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public Node() {}

    //Partial constructor for initializing only the Item within the Node
    public Node(String str) {
        myString = str;
    }

    //Full constuctor for creating a Node object and assigning an character name and next Node object pointer
    public Node(String str, Node next) {
        myString = str;
        myNext = next;
    }

    /* Accessors and Mutators */
    //Returns the character String of the Node object
    public String getMyString() {
        return myString;
    }

    //Returns the pointer for the next Node object in the list
    public Node getMyNext() {
        return myNext;
    }

    //Changes the value of the character String of a Node object to a new String
    public void setMyString(String myString) {
        this.myString = myString;
    }

    //Changes the pointer of the next pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyNext(Node myNext) {
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
