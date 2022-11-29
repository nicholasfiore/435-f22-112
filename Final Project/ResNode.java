/*
 * Modified version of the Node class to contain Resident objects, in order to keep track of residents that get bumped from a hospital
 * during the Stable Matching problem
 */
public class ResNode {
    /* Data Fields */
    private Resident myRes= null;
    private ResNode myNext = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public ResNode() {}

    //Partial constructor for initializing only the Item within the Node
    public ResNode(Resident res) {
        myRes = res;
    }

    //Full constuctor for creating a Node object and assigning an character name and next Node object pointer
    public ResNode(Resident res, ResNode next) {
        myRes = res;
        myNext = next;
    }

    /* Accessors and Mutators */
    //Returns the character String of the Node object
    public Resident getMyRes() {
        return myRes;
    }

    //Returns the pointer for the next Node object in the list
    public ResNode getMyNext() {
        return myNext;
    }

    //Changes the value of the character String of a Node object to a new String
    public void setMyRes(Resident myRes) {
        this.myRes = myRes;
    }

    //Changes the pointer of the next pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyNext(ResNode myNext) {
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
