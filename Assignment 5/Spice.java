/*
 * Spice to be used in the fractional knapsack problem using a greedy algorithm. Spice is the object being added to the knapsacks.
 * Spice has a quantity measured in scoops, which is the smallest fraction that the spice can be separated into. The spice also has 
 * a name and a total value, which is used to calculate its price per scoop.
 */

public class Spice {
    /* Data Fields */
    private String name = null;
    private double totPrice;
    private int quantity;
    private double pricePerScoop; //determined when the object is initialized

    /* Constuctors */

    public Spice() {
        //empty constuctor
    }

    public Spice(String name, double price, int qty) {
        this.name = name;
        this.totPrice = price;
        this.quantity = qty;
        recalculatePricePerScoop();
    }

    /* Accessors/Mutators */
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public double getPricePerScoop() {
        return pricePerScoop;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        recalculatePricePerScoop();
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
        recalculatePricePerScoop();
    }

    /* Functions */
    //recalculates
    public void recalculatePricePerScoop() {
        this.pricePerScoop = totPrice / quantity;
    }
}
