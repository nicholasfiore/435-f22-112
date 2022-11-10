public class Tree {
    /* Data Field */
    private TreeNode myRoot;

    /* Constructors */
    //empty default constructor
    public Tree() {
        
    }
    //constructor for starting with the root node
    public Tree(TreeNode root) {
        myRoot = root;
    }

    /* Accessor/Mutator */
    public TreeNode getMyRoot() {
        return this.myRoot;
    }

    public void setMyRoot(TreeNode myRoot) {
        this.myRoot = myRoot;
    }

    /* Functions */
    public void insert(TreeNode newNode) {
        TreeNode trailing = null;
        TreeNode current = this.myRoot;
        System.out.print("Inserting " + newNode.getMyString() + ": ");
        while (current != null) {
            trailing = current;
            if ((newNode.getMyString().toUpperCase()).compareTo(current.getMyString().toUpperCase()) < 0) {
                current = current.getMyLeft();
                System.out.print("L");
            }
            else {//must be >=
                current = current.getMyRight();
                System.out.print("R");
            }
            System.out.print(" ");
        }
        newNode.setMyParent(trailing);
        if (trailing != null) {
            if ((newNode.getMyString().toUpperCase()).compareTo(trailing.getMyString().toUpperCase()) < 0) {
                trailing.setMyLeft(newNode);
            }
            else { // >=
                trailing.setMyRight(newNode);
            }
        }
        else {
            this.setMyRoot(newNode);
            System.out.print("Root.");
        }
    System.out.println();
    }

    
}
