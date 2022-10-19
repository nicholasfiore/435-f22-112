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
            
            //
            int compCounter = 0;
            int currentIteration = 0;

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
            //sorts the array
            Sort.mergeSort(itemList, 0, itemList.length, temp);


            
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
