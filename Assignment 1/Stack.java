/* 
 * A class to be used for a Stack data structure. Stacks operate on a last in, first out structure. It has 3 operations, including push, pop, and isEmpty.
 * Stacks use the Node class for the elements in the Stack, and always keep track of the element at the top of the Stack 
 */

public class Stack {
    /* Data fields */
    private Node myHead = null;
    
    /* Accessor and Mutator*/
    //Returns the pointer of myHead
    public Node getMyHead() {
        return myHead;
    }

    //Changes the pointer of myHead
    public void setMyHead(Node myHead) {
        this.myHead = myHead;
    }

    /* Functions */
    //adds a new Node object to the Stack
    public void push(Node newNode) {
        newNode.setMyNext(getMyHead());
        this.setMyHead(newNode);
    }

    //removes a node object from the top of the stack and returns the node object
    public Node pop() {
        Node val = this.getMyHead();
        this.setMyHead(val.getMyNext());
        return val;
    }
}
