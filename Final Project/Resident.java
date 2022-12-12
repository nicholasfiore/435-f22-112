import java.util.ArrayList;

/*
 * A class to represent a Resident in the resident-hospital variation of the stable matching problem. Contains an ID, an arraylist of integer IDs that represent hospital ranks, and a boolean value
 * to represent whether or not the resident is free (not assigned a hospital). Converts the integer arraylist to an arraylist of Hospital objects based on ID, maintaining the same order. Also is
 * assigned a matched hospital once provisional assignment is finalized and no resident is free.
 */
public class Resident {
    private int id;
    private ArrayList<Integer> hosRankInt = null;
    private ArrayList<Hospital> hospitalRank = null;
    private Hospital matchedHospital = null; //allows the matches to be printed in order based on residents rather than hospital. Only initialized after stability is reached.
    private boolean isFree;

    //constructor
    public Resident(int id) {
        this.id = id;
        hosRankInt = new ArrayList<>();
        hospitalRank = new ArrayList<>();
        this.isFree = true;
    }

    /* Accesors & Mutators */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    public boolean isFree() {
        return isFree;
    }

    public ArrayList<Hospital> getHospitalRank() {
        return hospitalRank;
    }

    public void addHospital(int hospitalId) {
        hosRankInt.add(hospitalId);
    }

    public Hospital getMatchedHospital() {
        return matchedHospital;
    }

    public void setMatchedHospital(Hospital matchedHospital) {
        this.matchedHospital = matchedHospital;
    }

    /* Functions */
    //converts the arraylist of integer ids to a list of their corresponding Resident objects, given a list of residen objects. Uses a linear search to do so.
    public void idToObject(Hospital[] hosList) {
        hospitalRank = new ArrayList<>();
        for (int i = 0; i < hosRankInt.size(); i++) {
            hospitalRank.add(linearSearch(hosList, hosRankInt.get(i)));
        }
    }

    //modified linear search designed to find a Hospital based on an ID, and return the object itself
    public Hospital linearSearch(Hospital[] arr, int key) {
        int i = 0;
        while (i < arr.length && arr[i].getId() != key) {
            i++;
        }
        if (i >= arr.length)
            i = -1;
        return arr[i];
    }
}
