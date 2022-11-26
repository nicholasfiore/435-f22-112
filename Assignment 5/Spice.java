public class Spice {
    /* Data Fields */
    private String name = null;
    private double totPrice;
    private double quantity;

    /* Constuctors */

    public Spice() {
        //empty constuctor
    }

    public Spice(String name, double price, double qty) {
        this.name = name;
        totPrice = price;
        quantity = qty;
    }

    /* Accessors/Mutators */
    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotPrice() {
        return totPrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setTotPrice(double totPrice) {
        this.totPrice = totPrice;
    }
}
