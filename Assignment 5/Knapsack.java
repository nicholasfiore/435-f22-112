public class Knapsack {
    /* Data fields */
    private double capacity;
    private double currVolume;


    /* Constuctors */
    public Knapsack() {
        //default constructor
    }

    public Knapsack(double cap) {
        this.capacity = cap;
    }

    /* Accessors/Mutators */
    public double getCapacity() {
        return capacity;
    }

    public double getCurrVolume() {
        return currVolume;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public void setCurrVolume(double currVolume) {
        this.currVolume = currVolume;
    }
}
