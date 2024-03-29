package assignment_4;

//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
 * The purpose of this program is to explore both graphs and the ways to traverse them, as well as create a binary tree to be used for
 * the purpose of searching, similar to a binary search.
 */

public class MainFour {
    public static void main(String[] args) throws IOException {
        //variable to store comparisons 
        int[] compCounter = new int[1];

        createGraph("graphs1.txt");


        //array to hold all the items in the magic items list
        String[] itemList = null;

        itemList = fileToArray("magicitems.txt", itemList);

        // //array to hold the list of items that will be used to search the binary tree
        String[] searchList = null;

        searchList = fileToArray("magicitems-find-in-bst.txt", searchList);
        
        Tree binarySearchTree = createBinaryTree(itemList);

        System.out.print("In-Order Traversal: ");
        inOrderTraversal(binarySearchTree.getMyRoot());
        System.out.println();
        System.out.println();

        int average = searchTreeFromList(searchList, binarySearchTree, compCounter);

        System.out.println("Average # of comparisons: " + average);

    }

    //takes a file name and a list and puts each line of the file into a String in an array
    public static String[] fileToArray(String fileName, String[] list) throws IOException {
        long totalLines = 0;
        File file = new File(fileName);
        BufferedReader input = null;
        try {
            //gets the path of the current file in order to get the # of lines
            Path path = Paths.get(file.getName());

            input = new BufferedReader(new FileReader(fileName)); 

            totalLines = Files.lines(path).count();

            list = new String[(int)totalLines];

            for(int i = 0; i < totalLines; i++) {
                list[i] = input.readLine();
            }

        } catch(FileNotFoundException ex) {
            System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(IOException ex) {
            System.out.println(ex);
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

        return list;
    }

    //takes in an array of Strings and adds the entirety of the array to a Tree, before returning that Tree object.
    public static Tree createBinaryTree(String[] list) {
        Tree tree = new Tree();
        for (int i = 0; i < list.length; i++) {
            tree.insert(new TreeNode(list[i]));
        }
        System.out.println();
        return tree;
    }

    //calls the searchBinaryTree function for every String in the tree, then computes the average and returns it.
    public static int searchTreeFromList(String[] list, Tree bst, int[] comparisons) {
        int total = 0;
        int average;
        for (int i = 0; i < list.length; i++) {
            Search.searchBinaryTree(bst, list[i], comparisons);
            total += comparisons[0];
        }
        average = total / list.length;
        return average;
    }

    //searches a tree using in-order traversal
    public static void inOrderTraversal(TreeNode node) {
        if(node == null)
            return;

        inOrderTraversal(node.getMyLeft());
        
        System.out.print("[" + node.getMyString() + "] ");
        
        inOrderTraversal(node.getMyRight());
    }
    

    //Creates graphs from instructions given in a file
    public static int createGraph(String fileName) throws IOException {
        long totalLines = 0;
        File file = new File(fileName);
        BufferedReader input = null;
        String line;
        String nextLine;
        try {
            //gets the path of the current file in order to get the # of lines
            Path path = Paths.get(file.getName());

            input = new BufferedReader(new FileReader(fileName)); 

            totalLines = Files.lines(path).count();

            //instantiate variables for graph and vertex objects. There are two Vertex objects in the case of adding edges.
            Graph graph = null;
            Vertex vert1 = null;
            Vertex vert2 = null;

            nextLine = input.readLine();

            //Command parsing. Goes through each line and determines what command is being used based on strings.
            for (int i = 0; i < totalLines; i++) {
                line = nextLine;
                nextLine = input.readLine();
                if (line.split(" ")[0].compareTo("--") == 0) {
                    //do nothing, ignore this line as it is a comment
                } else if (line.split(" ")[0].compareTo("new") == 0) { //new graph
                    //create a new graph object held by the graph variable
                    graph = new Graph();

                } else if (line.split(" ")[0].compareTo("add") == 0) { //enters add vertex/add edge tree
                    if (line.split(" ")[1].compareTo("vertex") == 0) { //add vertex x
                        //adds a new vertex to the graph, parsing the ID from the line given
                        vert1 = new Vertex(Integer.parseInt(line.split(" ")[2]));
                        graph.addVertex(vert1);
                    } else if (line.split(" ")[1].compareTo("edge") == 0) { //add edge x - y
                        //adds a new edge to the graph, based on the IDs parsed from the line
                            //the verticies are found by searching the graph's verticies ArrayList for a Vertex that matches the ID given in the line at both positions
                        vert1 = Search.linearSearchReturnVertex(graph.getVerticies(), Integer.parseInt(line.split(" ")[2]));
                        vert2 = Search.linearSearchReturnVertex(graph.getVerticies(), Integer.parseInt(line.split(" ")[4]));
                        
                        graph.addEdge(vert1, vert2); //the verticies are now added as neighbors, forming an adjacency
                    }
                }
                //final check to see if the next line is either empty or does not exist 
                if (nextLine == null || nextLine.isEmpty()) { //empty space; commands for this graph are done, begin processing
                    graph.printMatrix();
                    graph.printAdjacencyList();
                    
                    System.out.print("Depth-First Traversal: ");
                    graph.depthFirstSearch(graph.getVerticies().get(0));
                    graph.resetProcessing();
                    System.out.println();
                    
                    graph.breadthFirstSearch(graph.getVerticies().get(0));
                    graph.resetProcessing();
                }
            }

        } catch(FileNotFoundException ex) {
            System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        } catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } finally {
            if (input != null) {
                input.close();
            }
        }

        return (int)totalLines;
    }
}
