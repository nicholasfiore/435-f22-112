//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/* 
 * The purpose of this program is to explore both graphs and the ways to traverse them, as well as create a binary tree to be used for
 * the purpose of searching, similar to a binary search.
 */

public class MainFive {
    public static void main(String[] args) throws IOException {
        //variable to store comparisons 
        int[] compCounter = new int[1];

        createGraph("graphs2.txt");

        System.out.println("Greedy Knapsack problem:");
        fractionalKnapsackSpiceHeist("spice.txt");
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

    //takes in a file and performs a greedy algoritm to solve a fractional knapsack problem for all spices and knapsacks
    public static int fractionalKnapsackSpiceHeist(String fileName) throws IOException {
        long totalLines = 0;
        File file = new File(fileName);
        BufferedReader input = null;
        String line;
        String nextLine;
        String currCmd; //keeps track of the current subcommand while parsing the file

        String[] cmdLine = null; //used to keep track of the current line for spices by assigning the String.split() String array to it

        //lists to keep track of the Spices and the Knapsacks
        ArrayList<Spice> spices = new ArrayList<Spice>();
        ArrayList<Knapsack> knapsacks = new ArrayList<Knapsack>();

        try {
            //gets the path of the current file in order to get the # of lines
            Path path = Paths.get(file.getName());

            input = new BufferedReader(new FileReader(fileName)); 

            totalLines = Files.lines(path).count();

            //instantiate variables for graph and vertex objects. There are two Vertex objects in the case of adding edges.
            Graph graph = null;
            Vertex vert1 = null;
            Vertex vert2 = null;
            int weight;

            nextLine = input.readLine();

            //Command parsing. Goes through each line and determines what command is being used based on strings.
            for (int i = 0; i < totalLines; i++) {
                line = nextLine;
                nextLine = input.readLine();
                line = line.trim().replaceAll(" +", " "); //reformats the String so that parsing is easier
                if (line.split(" ")[0].compareTo("--") == 0 || line.isEmpty()) {
                    //ignore line; it is a comment or blank space
                } else if (line.split(" ")[0].compareTo("spice") == 0) {
                    cmdLine = line.split(";"); //splits the current line based on ; (each line before a semicolon is a command)

                    String spiceName = null; //these values are used to assign to the Spice that will be added
                    double totalPrice = 0.0;
                    int qty = 0;

                    for (int j = 0; j < cmdLine.length; j++) {
                        currCmd = cmdLine[j].trim(); //ensures there are no leading spaces
                        if (currCmd.split(" ")[1].compareTo("name") == 0) {
                            spiceName = currCmd.substring(currCmd.lastIndexOf(" ")+1).split(";")[0];
                        } else if (currCmd.split(" ")[0].compareTo("total_price") == 0){
                            totalPrice = Double.parseDouble(currCmd.substring(currCmd.lastIndexOf(" ")+1).split(";")[0]);
                        } else if (currCmd.split(" ")[0].compareTo("qty") == 0){
                            qty = Integer.parseInt(currCmd.substring(currCmd.lastIndexOf(" ")+1).split(";")[0]);
                        }
                    }//end for
                    spices.add(new Spice(spiceName, totalPrice, qty));
                } else if (line.split(" ")[0].compareTo("knapsack") == 0) {
                    int cap = Integer.parseInt(line.substring(line.lastIndexOf(" ")+1).split(";")[0]); //parses the capacity of the knapsack
                    knapsacks.add(new Knapsack(cap));
                }

                //final check to see if the end of the file was reached
                if (nextLine == null) { //file has been parsed, begin processing
                    spiceInsertionSort(spices);
                    for(int j = 0; j < knapsacks.size(); j++) {
                        greedyKnapsack(spices, knapsacks.get(j));
                    }
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

    //the greedy algorithm used by fractionalKnapsackSpiceHeist(). The algorithm assumes the list of spices is already in descending order in terms of price per scoop of spice, meaning the highest price per scoop spice is first
    public static void greedyKnapsack(ArrayList<Spice> spiceList, Knapsack sack) {
        int capacity = sack.getCapacity();
        int i = 0;
        //variables to store for printing later
        ArrayList<Spice> stolenSpices = new ArrayList<>(); //keeps track of the spice piles that were placed into the sack
        ArrayList<Integer> scoopList = new ArrayList<>(); //keeps track of how many scoops of each spice were taken. Indexes here are a 1:1 correlation to the Spice in stolenSpices

        while (i < spiceList.size() && sack.getCurrVolume() < capacity) {
            Spice currSpice = spiceList.get(i);
            stolenSpices.add(currSpice);
            int remSpice = currSpice.getQuantity();
            int scoops = 0;
            while (remSpice > 0 && sack.getCurrVolume() < capacity) {
                sack.addScoop(currSpice);
                remSpice -= 1;
                scoops += 1;
            }
            scoopList.add(scoops);
            i++;
        }

        String prntStr = "";
        System.out.print("A knapsack of capacity " + capacity + " is worth " + sack.getValue() + " quatloos and contains ");
        for (i = 0; i < stolenSpices.size() - 1; i++) {
            prntStr += scoopList.get(i) + " scoop(s) of " + stolenSpices.get(i).getName() + ", ";
        }
        prntStr += "and " + scoopList.get(i) + " scoop(s) of " + stolenSpices.get(i).getName() + ".";
        System.out.println(prntStr);
    }

    //sorts a list of Spices based on their price per scoop
    public static void spiceInsertionSort(ArrayList<Spice> list) {
        //the sorting algorithm
        for (int i = 1; i < list.size(); i++) {
            Spice key = list.get(i);
            int j;

            for (j = i - 1; j >= 0 && key.getPricePerScoop() > list.get(j).getPricePerScoop(); j--) {
                //arr[j + 1] = arr[j];
                list.set(j + 1, list.get(j));
            }
            //arr[j + 1] = key;
            list.set(j + 1, key);
        }
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
            int weight;

            nextLine = input.readLine();

            //Command parsing. Goes through each line and determines what command is being used based on strings.
            for (int i = 0; i < totalLines; i++) {
                line = nextLine;
                nextLine = input.readLine();
                line = line.trim().replaceAll(" +", " "); //reformats the String so that parsing is easier
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
                    } else if (line.split(" ")[1].compareTo("edge") == 0) { //add edge x -> y with weight z
                        //adds a new edge to the graph, based on the IDs parsed from the line
                            //the verticies are found by searching the graph's verticies ArrayList for a Vertex that matches the ID given in the line at both positions
                        vert1 = Search.linearSearchReturnVertex(graph.getVerticies(), Integer.parseInt(line.split(" ")[2]));
                        vert2 = Search.linearSearchReturnVertex(graph.getVerticies(), Integer.parseInt((line).split(" ")[4]));
                        weight = Integer.parseInt(line.split(" ")[5]);

                        graph.addEdge(vert1, vert2, weight); //the verticies are now added as neighbors, forming an adjacency
                    }
                }
                //final check to see if the next line is either empty or does not exist 
                if (nextLine == null || nextLine.isEmpty()) { //empty space; commands for this graph are done, begin processing
                    boolean pathFound = graph.singleSourceShortestPath(graph.getVerticies().get(0));
                    if (!pathFound)
                        System.out.println("There was no path found due to a negative loop.");
                    else {

                    }
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
