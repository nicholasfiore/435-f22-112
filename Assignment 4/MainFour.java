//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/* 
 * The purpose of this program is TODO
 */

public class MainFour {
    public static void main(String[] args) throws IOException {
        //int variables to store the total lines of a .txt, then ints to store them per file
        int magicLines; //lines in magicitems.txt
        int treeLines; //lines in magicitems-find-in-bst.txt
        int graphLines; //lines in graphs1.txt

        //variable to store comparisons 
        int[] compCounter = new int[1];

        //array to hold all the items in the magic items list
        String[] itemList = null;

        itemList = fileToArray("magicitems.txt", itemList);

        //array to hold the list of items that will be used to search the binary tree
        String[] searchList = null;

        searchList = fileToArray("magicitems-find-in-bst.txt", searchList);
        
        Tree binarySearchTree = createBinaryTree(itemList);

        searchTreeFromList(searchList, binarySearchTree, compCounter);

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
    

    //Creates graphs from instructions given in a file
    public static int createGraph(String fileName) throws IOException {
        long totalLines = 0;
        File file = new File(fileName);
        BufferedReader input = null;
        String line;
        try {
            //gets the path of the current file in order to get the # of lines
            Path path = Paths.get(file.getName());

            input = new BufferedReader(new FileReader(fileName)); 

            totalLines = Files.lines(path).count();

            //instantiate variables for graph and vertex objects. There are two Vertex objects in the case of adding edges.
            Graph graph = null;
            Vertex vert1 = null;
            Vertex vert2 = null;



            for (int i = 0; i < totalLines; i++) {
                line = input.readLine();
                if (line.split(" ")[0].compareTo("--") == 0) {
                    //do nothing, ignore this line as it is a comment
                } else if (line.split(" ")[0].compareTo("new") == 0) {
                    //create a new graph object held by the graph variable
                    graph = new Graph();

                } else if (line.split(" ")[0].compareTo("add") == 0) {
                    if (line.split(" ")[1].compareTo("vertex") == 0) {
                        //adds a new vertex to the graph, parsing the ID from the line given
                        vert1 = new Vertex(Integer.parseInt(line.split(" ")[2]));
                        graph.addVertex(vert1);
                    } else if (line.split(" ")[1].compareTo("edge") == 0) {
                        //adds a new edge to the graph, based on the IDs parsed from the line
                            //the verticies are found by searching the graph's verticies ArrayList for a Vertex that matches the ID given in the line at both positions
                        vert1 = Search.linearSearch(graph.getVerticies(), Integer.parseInt(line.split(" ")[2]));
                        vert2 = Search.linearSearch(graph.getVerticies(), Integer.parseInt(line.split(" ")[4]));
                        
                        graph.addEdge(vert1, vert2); //the verticies are now added as neighbors, forming an adjacency
                    }
                }
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

        return (int)totalLines;
    }
}
