/*
 * The Node class is used to created linked lists of objects in order to more variably 
 */
public class Node {
    String myItem = "";
    Node myNext = null;

    //No-arg (default) constructor for creating a default Node object
    public Node() {}

    //Full constuctor for creating a Node object and assigning an item name and next Node object pointer
    public Node(String item, Node next) {
        myItem = item;
        myNext = next;
    }



}
