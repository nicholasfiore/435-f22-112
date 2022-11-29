import java.util.ArrayList;

public class Resident {
    private int id;
    private ArrayList<Integer> hosRankInt = null;
    private ArrayList<Hospital> hospitalRank = null;
    private boolean isFree;

    public Resident(int id) {
        this.id = id;
        hosRankInt = new ArrayList<>();
        hospitalRank = new ArrayList<>();
        this.isFree = true;
    }

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
