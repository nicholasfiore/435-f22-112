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
        while (current != null) {
            trailing = current;
            if (newNode.getMyString().compareTo(current.getMyString()) < 0) {
                current = current.getMyLeft();
                System.out.print("L, ");
            }
            else {//must be >=
                current.getMyRight();
                System.out.print("R, ");
            }
        }
        newNode.setMyParent(trailing);
        if (trailing != null) {
            if (newNode.getMyString().compareTo(trailing.getMyString()) < 0) {
                trailing.setMyLeft(newNode);
                System.out.print("L");
            }
            else { // >=
                trailing.setMyRight(newNode);
                System.out.print("R");
            }
        }
        else
            this.setMyRoot(newNode);
    }
}
