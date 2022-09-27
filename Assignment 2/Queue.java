/* 
 * A class to be used for a Queue data structure. Queues operate on a first in, first out basis. Operations perfomable by a queue include
 * enqueue, dequeue, and isEmpty. Queues use the Node class as entities within the queue object, and keep track of both the beginning and
 * the end of the queue.
 */
public class Queue {
    /* Data Fields */
    private Node myHead = null;
    private Node myTail = null;

    /* Constructors */
    //Empty default constructor
    public Queue() { /* Default Constuctor */ }

    /* Accessors and Mutators */
    //returns the pointer value of myHead
    public Node getMyHead() {
        return myHead;
    }
    //returns the pointer value of myTail
    public Node getMyTail() {
        return myTail;
    }
    //sets the value of myHead to a new Node object pointer
    public void setMyHead(Node myHead) {
        this.myHead = myHead;
    }
    //sets the value of myTail to a new Node object pointer
    public void setMyTail(Node myTail) {
        this.myTail = myTail;
    }

    /* Functions */
    //Adds a new Node object to the end of the queue. If the queue is empty, the new Node object becomes both the head and the tail of
    //the queue.
    public void enqueue(Node newNode) {
        if (this.isEmpty()) {
            this.setMyHead(newNode);
            this.setMyTail(newNode);
        } else {
            this.getMyTail().setMyNext(newNode);
            this.setMyTail(newNode);
        }
    }

    //Removes the Node object at the front of the queue and returns it.
    public Node dequeue() {
        Node retVal = this.getMyHead();
        this.setMyHead(retVal.getMyNext());
        return retVal;
    }

    //Checks to see if the queue is empty, returns a boolean.
    public boolean isEmpty() {
        if (myHead == null) {
            return true;
        } else {
            return false;
        }
    }
}
