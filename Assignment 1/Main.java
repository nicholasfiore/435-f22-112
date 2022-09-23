//Utility imports
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/* 
 * The purpose of this program is to take in a file with items, words, phrases, etc. each on separate lines and use various data structures such as linked lists, stacks,
 * and queues, in order to tell if said item/word/phrase is a palindrome (ignoring spaces).
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
            * This is the main section of the program. It performs all the operations necessary to find the palindromes in a .txt list,
            * including initializing a Stack and Queue object, adding the characters from each word into the Stack and Queue by putting
            * them into Node objects and pushing and enqueuing them, then popping and dequeuing them to compare the letters.
            */
            Queue itemQueue = new Queue();
            Stack itemStack = new Stack();
            //counts how many palindrones are in the list
            int counter = 0;
            //flag that is dependent on whether or not an item is a palindrome
            boolean flag;
            //temporary String object to store the String in the array and remove spaces/convert to uppercase
            String tempStr = "";

            //for loop goes through every line within the file
            for (int i = 0; i < totLines; i++) {
                //resets flag to true (a word is considered a palindrome until it is proven not)
                flag = true;
                tempStr = itemList[i].replaceAll("\\s", "").toUpperCase();
                
                //pushes each character sequentially into a stack and a queue
                for (int j = 0; j < tempStr.length(); j++) {
                    itemQueue.enqueue(new Node(tempStr.charAt(j)));
                    itemStack.push(new Node(tempStr.charAt(j)));
                }

                //sequentially pops each Node from the stack and dequeues each node from the queue together. If at any point the character
                //in the Node object of the popped object does not match the character in the Node that was dequeued, flag is set to false
                //as the word cannot be a palindrome in that case.
                for (int j = 0; j < tempStr.length(); j++) {
                    if (itemQueue.dequeue().getMyChar() != itemStack.pop().getMyChar())
                        flag = false;
                }

                //if the flag is set to true still, the word is a palindrome and is printed, and the counter is incremented
                if (flag) {
                    System.out.println(itemList[i]);
                    counter++;
                }
            }
            System.out.println("\nTotal palindromes: " + counter);
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
        }
    }
}
