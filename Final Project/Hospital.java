import java.util.ArrayList;

/*
 * A class to represent a Hospital in the resident-hospital version of the stable matching problem. Has an ID, integer arraylist of IDs representing hospitals, and capacity. The ranked ID list
 * are converted to an arraylist of Resident objects based on ID, maintaining that same order. Residents that have been provisionally assigned to the hospital are also kept track of in a list.
 */
public class Hospital {
    private int id;
    private ArrayList<Integer> resRankInt = null; //specifically used while parsing. After the file is fully parsed, converted into the Resident ArrayList
    private ArrayList<Resident> residentRank = null;
    private ArrayList<Resident> consideredResidents = null;
    private int capacity;
    
    //contructor (no other constuctor necessary)
    public Hospital(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        resRankInt = new ArrayList<>();
        consideredResidents = new ArrayList<>();
    }

    /* Accessors & Mutators */
    public ArrayList<Resident> getResidentRank() {
        return residentRank;
    }

    public ArrayList<Resident> getConsideredResidents() {
        return consideredResidents;
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

    /* Functions */
    //assigns a resident tentatively to be considered by the hospital
    public void assignResident(Resident resident) {
        consideredResidents.add(resident);
    }

    //bumps a considered resident from consideration
    public void bumpResident(Resident resident) {
        consideredResidents.remove(resident);
        resident.setFree(true);
    }

    public boolean isFull() {
        boolean retVal = false;
        if (consideredResidents.size() >= capacity) 
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

    //checks to see if a particular resident is being considered by linearaly searching the consideredResidents list
    public boolean isConsidering(Resident res) {
        boolean retVal = false;
        int i = 0;
        while (i < consideredResidents.size() && consideredResidents.get(i) != res) {
            i++;
        }
        if (i != consideredResidents.size() && consideredResidents.get(i) == res) 
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
