public class Knapsack {
    /* Data fields */
    private int capacity;
    private double currVolume;
    private double value;


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

    public double getValue() {
        return value;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setCurrVolume(double currVolume) {
        this.currVolume = currVolume;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /* Functions */
    //adds a scoop to the sack, including the value of that scoop
    public void addScoop(Spice spice) {
        this.currVolume += 1;
        this.value += spice.getPricePerScoop();   
    }
}
