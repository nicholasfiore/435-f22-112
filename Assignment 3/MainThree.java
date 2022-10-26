//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
 * The purpose of this program is TODO
 */

public class MainThree {
    public static void main(String[] args) {
        //creates a new file object for the magicitems.txt file
        String fileName = "magicitems.txt";
        File file = new File(fileName);
        //long variable to store the total lines of a .txt
        long totLines;
        //array to hold all the items in the list
        String[] itemList;

        
        /* This try-catch block is for reading in a .txt file, putting each line onto an array, and throwing exceptions if there are any. */
        try {
            //Scanner object for scanning the file
            Scanner fileScanner = new Scanner(file);
            Path path = Paths.get(fileName);
            
            //grabs the amount of lines within the .txt file and prints it
            totLines = Files.lines(path).count();
            System.out.println("Total lines: " + totLines + "\n");

            //initializes the size of the array with the total lines in the .txt
            itemList = new String[(int)totLines];

            
            for (int i = 0; i < totLines; i ++) {
                itemList[i] = fileScanner.nextLine();
            }
            
            //closes the scanner after use to save resources
            fileScanner.close();


            /* 
             * 
             */
            

            //counters for the comparisons and the current search. Search count starts at 1, and will go to 42
            int[] compCounter = new int[1];
            compCounter[0] = 0;
            int currentSearch = 1;

            //array for storing the comparison counts of each search, to be used to find the average
            int[] averages = new int[42];

            //small subprogram to get 42 random items from the list. By shuffling the original list randomly, the first 42 elements
            //will be randomly "selected" by nature of a random shuffle and then just using those values. Technically, any range of
            //42 elements within the original list would work, as they would all be randomized.
            String[] randList = new String[42];
            Sort.shuffle(itemList);
            for (int i = 0; i < 42; i++) {
                randList[i] = itemList[i];
            }

            //temporary int array to use mergeSort without having to change it completely
            int[] temp = new int[1];
            temp[0] = 0;

            //sorts the array using a mergeSort algorithm
            Sort.mergeSort(itemList, 0, itemList.length - 1, temp);

            /* Linear Search */
            System.out.println("\033[1mLinear Search\033[0m");
            //displays the comparisons for every Linear search made
            for (int i = 0; i < randList.length; i++) {
                int index = Search.linearSearch(itemList, randList[i], compCounter);
                System.out.println("Search " + currentSearch + " number of comparisons: \033[1m" + compCounter[0] + "\033[0m. Key (" + randList[i] + ") was found at index " + index);
                averages[i] = compCounter[0];
                compCounter[0] = 0;
                currentSearch++;
            }

            //calculates and displays the average of all the searches
            int total = 0;
            for (int j = 0; j < averages.length; j++) {
                total += averages[j];
            }
            int average = total / averages.length;
            System.out.println("Average number of comparisons: " + average);
            System.out.println();
            
            //resets counters and other values to default
            compCounter[0] = 0;
            currentSearch = 1;
            average = 0;
            total = 0;

            /* Binary Search */
            System.out.println("\033[1mBinary Search\033[0m");
            //
            for (int i = 0; i < randList.length; i++) {
                int index = Search.binarySearch(itemList, 0, itemList.length, randList[i], compCounter);
                System.out.println("Search " + currentSearch + " number of comparisons: \033[1m" + compCounter[0] + "\033[0m. Key (" + randList[i] + ") was found at index " + index);
                averages[i] = compCounter[0];
                compCounter[0] = 0;
                currentSearch++;
            }

            //calculates and displays the average of all the searches
            total = 0;
            for (int j = 0; j < averages.length; j++) {
                total += averages[j];
            }
            average = total / averages.length;
            System.out.println("Average number of comparisons: " + average);
            System.out.println();

            //resets counters and other values to default
            compCounter[0] = 0;
            currentSearch = 1;
            average = 0;
            total = 0;

            /* Hashing */
            //adds all the items in the list to the hash table in Hashing.java
            for (int i = 0; i < itemList.length; i++) {
                Hashing.put(itemList[i]);
            }

            //the list of items to be searched with the hash table. Numbers were all chosen randomly once with an external number generator.
            int[] choiceList = new int[]{24, 45, 64, 80, 111, 114, 123, 152, 192, 205, 225, 232, 236, 249, 262, 269, 301, 302, 320, 324, 339,
                355, 366, 387, 411, 434, 444, 445, 460, 464, 477, 491, 507, 545, 556, 577, 581, 604, 615, 639, 657, 665};

            //
            for (int i = 0; i < choiceList.length; i++) {
                String key = itemList[choiceList[i]];
                int hash = Hashing.makeHashCode(key);
                boolean wasFound = Hashing.get(hash, key, compCounter);
                if (wasFound) {
                    System.out.println(key + " was found after " + compCounter[0] + " comparisons.");
                } else {
                    System.out.println(key + " was not found.");
                }
                compCounter[0] = 0;
            }
            
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
