/*
 * This class is used to create and maintain linked lists of Node objects.
 */
public class LinkedList {
    /* Data Fields */
    private Node myHead = null;
    private Node myTail = null;

    /* Constructors */
    //empty constructor
    LinkedList() {
        /* empty */
    }
    //full constructor - adds the first item of the list
    LinkedList(Node start) {
        myHead = start;
        myTail = start;
    }

    /* Accessors and Mutators */
    public Node getMyHead() {
        return myHead;
    }

    public Node getMyTail() {
        return myTail;
    }

    public void setMyHead(Node myHead) {
        this.myHead = myHead;
    }

    public void setMyTail(Node myTail) {
        this.myTail = myTail;
    }

    /* Functions */
    //adds a node object to the FRONT of the list. Functions the same as in a Stack.
    public void push(Node newNode) {
        newNode.setMyNext(this.myHead);
        this.setMyHead(newNode);
    }

    //removes from the FRONT of the list and returns it. Functions the same as in a Stack.
    public Node pop() {
        Node val = this.getMyHead();
        this.setMyHead(val.getMyNext());
        return val;
    }

    //adds a node object to the END of the list.
    public void add(Node newNode) {
        myTail.setMyNext(newNode);
        this.setMyTail(newNode);
    }

    //removes from the END of the list and returns it.
    public Node remove() {
        Node val = this.getMyTail();
        Node current = this.getMyHead();
        while (!(current.getMyNext() == val)) {
            current = current.getMyNext();
        }
        current.setMyNext(null);
        return val;
    }

    //checks if the list is empty
    public boolean isEmpty() {
        boolean retVal = false;
        if (this.myHead == null)
            retVal = true;
        return retVal;
    }

}
