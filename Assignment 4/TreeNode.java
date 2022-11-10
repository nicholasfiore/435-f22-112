/*
 * The Node class is used to created linked lists of objects in order to more variably control the size of said list without being locked into an array
 * This is a modified version of the Node class to be used to construct binary trees.
 */
public class TreeNode {
    /* Data Fields */
    private String myString = "";
    private TreeNode myParent = null;
    private TreeNode myLeft = null;
    private TreeNode myRight = null;

    /* Constructors */
    //No-arg (default) constructor for creating a default Node object
    public TreeNode() {}

    //Partial constructor for initializing only the Item within the Node
    public TreeNode(String str) {
        myString = str;
    }

    //Full constuctor for creating a Node object and assigning an character name and next Node object pointer for left and right
    public TreeNode(String str, TreeNode left, TreeNode right) {
        myString = str;
        myLeft = left;
        myRight = right;
    }

    /* Accessors and Mutators */
    //Returns the character String of the Node object
    public String getMyString() {
        return myString;
    }

    public TreeNode getMyParent() {
        return myParent;
    }

    //Returns the pointer for the next Node object to the left
    public TreeNode getMyLeft() {
        return myLeft;
    }

    //Returns the pointer for the next Node object to the right
    public TreeNode getMyRight() {
        return myRight;
    }

    //Changes the value of the character String of a Node object to a new String
    public void setMyString(String myString) {
        this.myString = myString;
    }

    public void setMyParent(TreeNode myParent) {
        this.myParent = myParent;
    }

    //Changes the pointer of the left pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyLeft(TreeNode myLeft) {
        this.myLeft = myLeft;
    }

    //Changes the pointer of the right pointer of a Node object to a new pointer to a node Object (or NULL)
    public void setMyRight(TreeNode myRight) {
        this.myRight = myRight;
    }

    /* Functions */
    //Checks to see if the Node object has a myLeft or myRight value != null. Returns boolean depending on result.
    public boolean hasLeft() {
        if (this.getMyLeft() != null)
            return true;
        else
            return false;
    }

    public boolean hasRight() {
        if (this.getMyRight() != null)
            return true;
        else
            return false;
    }

}
