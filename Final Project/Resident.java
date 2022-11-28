import java.util.ArrayList;

public class Resident {
    private ArrayList<Hospital> hospitalRank = null;

    public Resident() {
        hospitalRank = new ArrayList<>();
    }

    public ArrayList<Hospital> getHospitalRank() {
        return hospitalRank;
    }

    public void addHospital(Hospital hospital) {
        hospitalRank.add(hospital);
    }
}
