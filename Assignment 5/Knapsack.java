public class Knapsack {
    /* Data fields */
    private int capacity;
    private double currVolume;


    /* Constuctors */
    public Knapsack() {
        //default constructor
    }

    public Knapsack(int cap) {
        this.capacity = cap;
    }

    /* Accessors/Mutators */
    public int getCapacity() {
        return capacity;
    }

    public double getCurrVolume() {
        return currVolume;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCurrVolume(double currVolume) {
        this.currVolume = currVolume;
    }
}
