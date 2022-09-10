public class Main {
    public static void main(String[] args) {
        Node test = new Node();
        //testing default values for an empty constructor
        System.out.println(test.getMyItem());
        System.out.println(test.getMyNext());

        test.setMyItem("Bag of Holding");
        
        System.out.println(test.getMyItem());
    }
}
