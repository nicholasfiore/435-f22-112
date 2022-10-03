//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
 * The purpose of this program is TODO
 */

public class MainTwo {
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
            Sort.shuffle(itemList);
            Sort.selectionSort(itemList);
            
            Sort.shuffle(itemList);
            Sort.insertionSort(itemList);

            Sort.shuffle(itemList);
            long mergeStart = System.nanoTime();
            Sort.mergeSort(itemList, 0, (itemList.length-1));
            long mergeEnd = System.nanoTime();
            //Print statements including formatting for mergeSort()
            System.out.println("\033[1mMerge Sort\033[0m");
            System.out.print("Number of comparisons: ");
            System.out.printf("%,5d %n", Sort.mergeCount);
            System.out.printf("%-21s", "Time elapsed");
            System.out.print(": ");
            System.out.printf("%,5d", ((mergeEnd - mergeStart) / 1000));
            System.out.println(" µs\n");
            
            // for (int i = 0; i < itemList.length; i++) {
            //     System.out.println(itemList[i]);
            // }
            
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
        }
    }
}
