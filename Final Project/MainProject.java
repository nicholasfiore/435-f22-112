import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainProject {
    public static void main(String[] args) throws IOException {
        stableMatchingProblem("final-project-data.txt");
        System.out.println();
        stableMatchingProblemNoHospitalRank("final-project-data-no-hospital-rank.txt");
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
                    hospitals = new Hospital[Integer.parseInt(lineArr[2])];
                    residents = new Resident[Integer.parseInt(lineArr[1])];
                } else if (line.charAt(0) == 'r') {
                    id = Integer.parseInt(lineArr[0].substring(1, lineArr[0].length() - 1));
                    residents[resPos] = new Resident(id);
                    for (int j = 1; j < lineArr.length; j++) {
                        residents[resPos].addHospital(Integer.parseInt(lineArr[j].substring(1)));
                    }

                    resPos++; //increments the position in the array
                } else if (line.charAt(0) == 'h') { //initializes the rankings of each resident. First index is first choice, last is last choice.
                    id = Integer.parseInt(lineArr[0].substring(1, lineArr[0].length() - 1));
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

            ResQueue bumpedQueue = new ResQueue(); //queue for any residents that get bumped from a hospital
            for (int i = 0; i < residents.length; i++) {
                while (!bumpedQueue.isEmpty()) {
                    matchingAlgorithm(bumpedQueue, bumpedQueue.dequeue().getMyRes());
                }
                matchingAlgorithm(bumpedQueue, residents[i]);
            }

            System.out.println("Match:");
            for (int i = 0; i < hospitals.length; i++) { //adds all hospitals to their resident's matchedHospital member
                Hospital currHos = hospitals[i];
                Resident currRes;
                for (int j = 0; j < currHos.getConsideredResidents().size(); j++) {
                    currRes = currHos.getConsideredResidents().get(j);
                    currRes.setMatchedHospital(currHos);
                }
            }

            for (int i = 0; i < residents.length; i++) {
                int resId = residents[i].getId();
                int hosId = residents[i].getMatchedHospital().getId();
                System.out.println("(r" + resId + ", h" + hosId + ")");
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
    public static void matchingAlgorithm(ResQueue bumped, Resident res) {
        Hospital topChoice = res.getHospitalRank().get(0); //returns the current top available choice of the resident
        //initial addition
        if (!topChoice.isFull()) {
            topChoice.assignResident(res);
            res.setFree(false);
        } else {
            int i = topChoice.getResidentRank().size() - 1;
            Resident currRes = null;
            boolean resFound = false;
            while (i >= 0 && !resFound) {
                currRes = topChoice.getResidentRank().get(i);
                if (topChoice.isConsidering(currRes))    
                    resFound = true;
                else
                    i--;
            }
            if (topChoice.getResidentRank().indexOf(res) < topChoice.getResidentRank().indexOf(currRes)) { //ensures that the new resident is ranked higher before bumping the old resident
                //bump lower ranked resident
                topChoice.bumpResident(currRes);
                bumped.enqueue(new ResNode(currRes));
                
                //add new resident
                topChoice.assignResident(res);
                res.setFree(false);
            }
        }

        //post check. If a hospital was just filled up, all residents after its last ranked resident being considered are dropped from the running.
        if(topChoice.isFull()) {
            int i = topChoice.getResidentRank().size() - 1;
            Resident currRes = null;
            boolean resFound = false;
            while (i >= 0 && !resFound) {
                currRes = topChoice.getResidentRank().get(i);
                if (topChoice.isConsidering(currRes))    
                    resFound = true;
                else
                    i--;
            }
            Resident removedRes = null;
            int removedResHospitalIndex;
            while (topChoice.getResidentRank().size() > i + 1) { //removes all elements in the list after index i
                removedRes = topChoice.getResidentRank().remove(i + 1);
                removedResHospitalIndex = removedRes.getHospitalRank().indexOf(topChoice);
                if (removedResHospitalIndex != -1) //if the removed resident has the hospital in its rankings, remove it
                    removedRes.getHospitalRank().remove(removedResHospitalIndex);
            }
        }
    }

    public static void stableMatchingProblemNoHospitalRank(String fileName) throws IOException {
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
                    hospitals = new Hospital[Integer.parseInt(lineArr[2])];
                    residents = new Resident[Integer.parseInt(lineArr[1])];
                } else if (line.charAt(0) == 'r') {
                    id = Integer.parseInt(lineArr[0].substring(1, lineArr[0].length() - 1));
                    residents[resPos] = new Resident(id);
                    for (int j = 1; j < lineArr.length; j++) {
                        residents[resPos].addHospital(Integer.parseInt(lineArr[j].substring(1)));
                    }

                    resPos++; //increments the position in the array
                } else if (line.charAt(0) == 'h') { //initializes the rankings of each resident. First index is first choice, last is last choice.
                    id = Integer.parseInt(lineArr[0].substring(1, lineArr[0].length() - 1));
                    capacity = Integer.parseInt(lineArr[1]);
                    hospitals[hosPos] = new Hospital(id, capacity);

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

            ResQueue bumpedQueue = new ResQueue(); //queue for any residents that get bumped from a hospital
            for (int i = 0; i < residents.length; i++) {
                while (!bumpedQueue.isEmpty()) {
                    matchingAlgorithmNoHospitalRank(bumpedQueue, bumpedQueue.dequeue().getMyRes(), residents);
                }
                matchingAlgorithmNoHospitalRank(bumpedQueue, residents[i], residents);
            }

            System.out.println("Match:");
            for (int i = 0; i < hospitals.length; i++) { //adds all hospitals to their resident's matchedHospital member
                Hospital currHos = hospitals[i];
                Resident currRes;
                for (int j = 0; j < currHos.getConsideredResidents().size(); j++) {
                    currRes = currHos.getConsideredResidents().get(j);
                    currRes.setMatchedHospital(currHos);
                }
            }

            for (int i = 0; i < residents.length; i++) {
                int resId = residents[i].getId();
                int hosId = residents[i].getMatchedHospital().getId();
                System.out.println("(r" + resId + ", h" + hosId + ")");
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

    public static void matchingAlgorithmNoHospitalRank(ResQueue bumped, Resident res, Resident[] residents) {
        Hospital topChoice = res.getHospitalRank().get(0); //returns the current top available choice of the resident
        //initial addition
        if (!topChoice.isFull()) {
            topChoice.assignResident(res);
            res.setFree(false);
        } else {
            bumped.enqueue(new ResNode(res));
        }

        //post check. If a hospital was just filled up, all residents after its last ranked resident being considered are dropped from the running.
        if(topChoice.isFull()) {
            
            int hosIndex;
            for (int i = 0; i < residents.length; i++){ //removes the hospital from all of the residents that have it ranked
                hosIndex = residents[i].getHospitalRank().indexOf(topChoice);
                if (hosIndex != -1)
                    residents[i].getHospitalRank().remove(hosIndex);
            }
        }
    }
}
