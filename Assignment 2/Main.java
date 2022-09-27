//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
 * The purpose of this program is TODO
 */

public class Main {
    public static void main(String[] args) {
        /* creates a Scanner object for the input stream (keyboard), prompts the user for the filename/path and stores that into a variable, and then closes the scanner */
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the filename or path: ");
        String fileName = keyboard.nextLine();
        keyboard.close();

        //creates a new file object for the magicitems.txt file or other .txt file
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
            for (int i = 0; i < itemList.length; i++) {
                System.out.println(itemList[i]);
            }
            
            
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
        }
    }
}
