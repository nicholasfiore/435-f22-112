/*
 * The Node class is used to created linked lists of objects in order to more variably control the size of said list without being locked into an array
 */
public class Node {
    /* Data Fields */
    private char myChar = ' ';
    private Node myNext = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public Node() {}

    //Partial constructor for initializing only the Item within the Node
    public Node(char character) {
        myChar = character;
    }

    //Full constuctor for creating a Node object and assigning an character name and next Node object pointer
    public Node(char character, Node next) {
        myChar = character;
        myNext = next;
    }

    /* Accessors and Mutators */
    //Returns the character String of the Node object
    public char getMyChar() {
        return myChar;
    }

    //Returns the pointer for the next Node object in the list
    public Node getMyNext() {
        return myNext;
    }

    //Changes the value of the character String of a Node object to a new String
    public void setMyChar(char myChar) {
        this.myChar = myChar;
    }

    //Changes the pointer of the next pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyNext(Node myNext) {
        this.myNext = myNext;
    }

}
