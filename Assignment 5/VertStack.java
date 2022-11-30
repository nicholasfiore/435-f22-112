/* 
 * A class to be used for a Stack data structure. Stacks operate on a last in, first out structure. It has 3 operations, including push, pop, and isEmpty.
 * Stacks use the Node class for the elements in the Stack, and always keep track of the element at the top of the Stack.
 * This is a modified version of the Stack class that encapsulates Vertex objects into the nodes.
 */

public class VertStack {
    /* Data fields */
    private VertNode myHead = null;

    /* Constructor */
    //empty default constructor
    public VertStack() {}
    
    /* Accessor and Mutator*/
    //Returns the pointer of myHead
    public VertNode getMyHead() {
        return myHead;
    }

    //Changes the pointer of myHead
    public void setMyHead(VertNode myHead) {
        this.myHead = myHead;
    }

    /* Functions */
    //adds a new Node object to the Stack
    public void push(VertNode newNode) {
        newNode.setMyNext(this.getMyHead());
        this.setMyHead(newNode);
    }

    //removes a node object from the top of the stack and returns the node object
    public VertNode pop() {
        VertNode val = this.getMyHead();
        this.setMyHead(val.getMyNext());
        return val;
    }

    //checks to see if a stack is empty
    public boolean isEmpty() {
        if (myHead == null) {
            return true;
        } else {
            return false;
        }
    }
}
