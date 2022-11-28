import java.util.ArrayList;

public class Resident {
    private int id;
    private ArrayList<Integer> hospitalRank = null;

    public Resident(int id) {
        this.id = id;
        hospitalRank = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getHospitalRank() {
        return hospitalRank;
    }

    public void addHospital(int hospitalId) {
        hospitalRank.add(hospitalId);
    }
}
