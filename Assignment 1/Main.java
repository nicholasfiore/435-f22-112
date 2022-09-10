public class Main {
    public static void main(String[] args) {
        Node test1 = new Node();
        //testing default values for an empty constructor
        System.out.println(test1.getMyItem());
        System.out.println(test1.getMyNext());

        test1.setMyItem("Bag of Holding");
        
        System.out.println(test1.getMyItem());

        Node test2 = new Node("Bag of Devouring");
        //testing the partial constructor functionality
        System.out.println(test2.getMyItem());
        System.out.println(test2.getMyNext());

        test2.setMyNext(test1);

        System.out.println(test2.getMyNext());

        Node test3 = new Node("Handy Haversack", test2);
        //testing full constructor functionality
        System.out.println(test3.getMyItem());
        System.out.println(test3.getMyNext());

        System.out.println();
        //Testing linked list functionality by iterating through the list
        Node currentNode = test3;
        boolean flag = true;
        while (flag) {
            System.out.println(currentNode.getMyItem());
            System.out.println(currentNode.getMyNext());
            System.out.println();
            if (currentNode.getMyNext() == null) {
                flag = false;
            } else {
                currentNode = currentNode.getMyNext();
            }

        }
    }
}
