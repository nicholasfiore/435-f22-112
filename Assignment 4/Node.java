/*
 * The Node class is used to created linked lists of objects in order to more variably control the size of said list without being locked into an array
 */
public class Node {
    /* Data Fields */
    private String myString = "";
    private Node myLeft = null;
    private Node myRight = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public Node() {}

    //Partial constructor for initializing only the Item within the Node
    public Node(String str) {
        myString = str;
    }

    //Full constuctor for creating a Node object and assigning an character name and next Node object pointer for left and right
    public Node(String str, Node left, Node right) {
        myString = str;
        myLeft = left;
        myRight = right;
    }

    /* Accessors and Mutators */
    //Returns the character String of the Node object
    public String getMyString() {
        return myString;
    }

    //Returns the pointer for the next Node object to the left
    public Node getMyLeft() {
        return myLeft;
    }

    //Returns the pointer for the next Node object to the right
    public Node getMyRight() {
        return myRight;
    }

    //Changes the value of the character String of a Node object to a new String
    public void setMyString(String myString) {
        this.myString = myString;
    }

    //Changes the pointer of the left pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyLeft(Node myLeft) {
        this.myLeft = myLeft;
    }

    //Changes the pointer of the right pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyRight(Node myRight) {
        this.myRight = myRight;
    }

    /* Functions */
    //Checks to see if the Node object has a myLeft or myRight value != null. Returns boolean depending on result.
    public boolean hasNext() {
        if (this.getMyLeft() != null || this.getMyRight() != null) {
            return true;
        } else {
            return false;
        }
    }

}
