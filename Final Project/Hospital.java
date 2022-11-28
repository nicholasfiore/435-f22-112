import java.util.ArrayList;

public class Hospital {
    private ArrayList<Resident> residentRank = null;

    public Hospital() {
        residentRank = new ArrayList<>();
    }

    public ArrayList<Resident> getResidentRank() {
        return residentRank;
    }

    public void addResident(Resident resident) {
        residentRank.add(resident);
    }
}
