/*
 * The Node class is used to created linked lists of objects in order to more variably 
 */
public class Node {
    /* Data Fields */
    private String myItem = "";
    private Node myNext = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public Node() {}

    //Partial constructor for initializing only the Item within the Node
    public Node(String item) {
        myItem = item;
    }

    //Full constuctor for creating a Node object and assigning an item name and next Node object pointer
    public Node(String item, Node next) {
        myItem = item;
        myNext = next;
    }

    /* Accessors and Mutators */
    //Returns the item String of the Node object
    public String getMyItem() {
        return myItem;
    }

    //Returns the pointer for the next Node object in the list
    public Node getMyNext() {
        return myNext;
    }

    //Changes the value of the item String of a Node object to a new String
    public void setMyItem(String myItem) {
        this.myItem = myItem;
    }

    //Changes the pointer of the next pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyNext(Node myNext) {
        this.myNext = myNext;
    }

    /* Functions */

}
