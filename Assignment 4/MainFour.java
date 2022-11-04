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
        //long variable to store the total lines of a .txt, then ints to store them per file
        int magicLines; //lines in magicitems.txt
        int treeLines; //lines in magicitems-find-in-bst.txt
        int graphLines; //lines in graphs1.txt

        //array to hold all the items in the magic items list
        String[] itemList = null;

        magicLines = fileToArray("magicitems.txt", itemList);
    }

    //takes a file name and a list and puts each line of the file into a String in an array
    public static int fileToArray(String fileName, String[] list) throws IOException {
        long totalLines = 0;
        File file = new File(fileName);
        BufferedReader input = null;
        String line;
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

        return (int)totalLines;
    }
}
