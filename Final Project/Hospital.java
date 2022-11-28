import java.util.ArrayList;

public class Hospital {
    private int id;
    private ArrayList<Integer> residentRank = null;
    private int capacity;

    public Hospital(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        residentRank = new ArrayList<>();
    }

    public ArrayList<Integer> getResidentRank() {
        return residentRank;
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
        residentRank.add(resident);
    }
}
