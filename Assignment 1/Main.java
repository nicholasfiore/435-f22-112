//Utility imports
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("./Assignment 1/magicitems.txt");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                System.out.println(sc.nextLine());
            }
            //closes the scanner after use to save resources
            sc.close();
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
        }
        //testing the Stack class
        System.out.println();
        Node item1 = new Node("Bag of Holding");
        Node item2 = new Node("Bag of Devouring");
        Node item3 = new Node("Handy Haversack");

        Stack myStack = new Stack();

        myStack.push(item1);
        myStack.push(item2);
        myStack.push(item3);

        System.out.println(myStack.isEmpty());

        Node temp = myStack.getMyHead();
        while (temp != null) {
            System.out.println(myStack.pop().getMyItem());
            temp = temp.getMyNext();
        }

        System.out.println(myStack.getMyHead());

        System.out.println(myStack.isEmpty());

    }
}
