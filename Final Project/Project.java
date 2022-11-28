import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Project {
    public static void main(String[] args) throws IOException {
        stableMatchingProblem("final-project-data.txt");
    }

    public static void stableMatchingProblem(String fileName) throws IOException {
        long totalLines = 0;
        File file = new File(fileName);
        BufferedReader input = null;
        String line;


        try {
            //gets the path of the current file in order to get the # of lines
            Path path = Paths.get(file.getName());

            input = new BufferedReader(new FileReader(fileName)); 

            totalLines = Files.lines(path).count();

            //instantiate lists for hospitals and residents
            Hospital[] hospitals = null;
            Resident[] residents = null;

            String[] lineArr = null; //variable to hold the word array of the current line

            //used when initializing resident/hospital objects
            int id;
            int capacity;
            
            //positions in the hospitals and residents arrays, respectively
            int hosPos = 0;
            int resPos = 0; 

            //Command parsing. Goes through each line and determines what command is being used based on strings.
            for (int i = 0; i < totalLines; i++) {
                line = input.readLine();
                lineArr = line.split(" ");
                if (lineArr[0].compareTo("--") == 0) {
                    //do nothing; it is a comment
                } else if (lineArr[0].compareTo("Config:") == 0){ //initialize the array size of the total hospitals and residents
                    hospitals = new Hospital[Integer.parseInt(lineArr[1])];
                    residents = new Resident[Integer.parseInt(lineArr[2])];
                } else if (line.charAt(0) == 'r') {
                    id = Integer.parseInt(lineArr[0].substring(1, lineArr[0].length() - 2));
                    residents[resPos] = new Resident(id);
                    for (int j = 1; j < lineArr.length; j++) {
                        residents[resPos].addHospital(Integer.parseInt(lineArr[j].substring(1)));
                    }

                    resPos++; //increments the position in the array
                } else if (line.charAt(0) == 'h') { //initializes the rankings of each resident. First index is first choice, last is last choice.
                    id = Integer.parseInt(lineArr[0].substring(1, lineArr[0].length() - 2));
                    capacity = Integer.parseInt(lineArr[1]);
                    hospitals[hosPos] = new Hospital(id, capacity);
                    for (int j = 3; j < lineArr.length; j++) { //initializes the rankings of each hospital. First index is first choice, last is last choice.
                        hospitals[hosPos].addResident(Integer.parseInt(lineArr[j].substring(1)));
                    }

                    hosPos++; //increments the position in the array
                }   
            }
            
            //post file parsing method calls and other executions
            //immediate execution of the conversion methods for both lists
            for (int i = 0; i < hospitals.length; i++) {
                hospitals[i].idToObject(residents);
            }
            for (int i = 0; i < residents.length; i++) {
                residents[i].idToObject(hospitals);
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
    }

    //used in stableMatchingProblem()
    public static void matchingAlgorithm(Hospital[] hosList, Resident[] resList) {
        for (int i = 0; i < resList.length; i++) {
            
        }
    }
}
