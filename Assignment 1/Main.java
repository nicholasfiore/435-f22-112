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
        } catch(FileNotFoundException ex) {
			System.out.println("Failed to find file: " + file.getAbsolutePath());
        } catch(Exception ex) {
            System.out.println("Something went wrong.");
            System.out.println(ex.getMessage());
        }
    }
}
