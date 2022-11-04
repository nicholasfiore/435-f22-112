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
        //creates a new file object for the directory holding the text files, and then a list for holding the files
        File dir = new File("./txt-files/");
        File[] files = dir.listFiles();
        //long variable to store the total lines of a .txt, then ints to store them per file
        long totLines;
        int magicLines; //lines in magicitems.txt
        int treeLines; //lines in magicitems-find-in-bst.txt
        int graphLines; //lines in graphs1.txt

        //array to hold all the items in the magic items list
        String[] itemList;
        

        for (File file : files) {
            if (file.isFile()) {
                BufferedReader input = null;
                String line;
                try {
                //gets the path of the current file in order to get the # of lines
                Path path = Paths.get(file.getName());
                
                input = new BufferedReader(new FileReader(file));
                //grabs the amount of lines within the .txt file and prints it
                totLines = Files.lines(path).count();
                System.out.println("Total lines: " + totLines + "\n");

                if (file.getName() == "magicitems.txt") {
                    //initializes the size of the array with the total lines in the magicitems.txt
                    itemList = new String[(int)totLines];

                    //initizializes the array 
                    for (int i = 0; i < totLines; i ++) {
                        itemList[i] = input.readLine();
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
            }
        }
    }
}
