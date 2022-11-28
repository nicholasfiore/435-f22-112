import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hospital {
    private int id;
    private ArrayList<Integer> resRankInt = null; //specifically used while parsing. After the file is fully parsed, converted into the Resident ArrayList
    private ArrayList<Resident> residentRank = null;
    private ArrayList<Resident> currResidents = null;
    private int capacity;
    

    public Hospital(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        resRankInt = new ArrayList<>();
        currResidents = new ArrayList<>();
    }

    public ArrayList<Integer> getResidentRank() {
        return resRankInt;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void addResident(int resident) {
        resRankInt.add(resident);
    }

    public void assignResident(Resident resident) {
        currResidents.add(resident);
    }

    public boolean isFull() {
        boolean retVal = false;
        if (currResidents.size() >= capacity) 
            retVal = true;
        return retVal;
    }

    //converts the arraylist of integer ids to a list of their corresponding Resident objects, given a list of residen objects. Uses a linear search to do so.
    public void idToObject(Resident[] resList) {
        residentRank = new ArrayList<>();
        for (int i = 0; i < resRankInt.size(); i++) {
            residentRank.add(linearSearch(resList, resRankInt.get(i)));
        }
    }

    //checks to see if a particular resident is being considered by linearaly searching the currResidents list
    public boolean isConsidering(Resident res) {
        boolean retVal = false;
        int i = 0;
        while (i < currResidents.size() && currResidents.get(i) != res) {
            i++;
        }
        if (currResidents.get(i) == res) 
            retVal = true;
        return retVal;
    }

    //modified linear search designed to find a Resident based on an ID, and return the object itself
    public Resident linearSearch(Resident[] arr, int key) {
        int i = 0;
        while (i < arr.length && arr[i].getId() != key) {
            i++;
        }
        if (i >= arr.length)
            i = -1;
        return arr[i];
    }
}