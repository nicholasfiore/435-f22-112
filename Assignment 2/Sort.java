import java.lang.Math;
/* 
 * Class that will be used to store sorting methods. While these don't necessarily need to be in their own class, I prefer it to keep things organized (and potentially be
 * able to reuse in the future)
 */

public class Sort {
    //a method for shuffling an array based on the Knuth shuffle
    public static void shuffle(String[] arr) {
        String temp = "";
        int random;
        for (int i = arr.length - 1; i > 0; i--) {
            random = (int)(Math.random() * i);
            temp = arr[i];
            arr[i] = arr[random];
            arr[random] = temp;
        } 
    }

    public static void selectionSort(String[] arr) {
        //these two are used for determining the amount of time elapsed during the method execution
        long start = System.nanoTime();
        long end;
        //a counter to be used for counting each comparison within the sort.
        int compCounter = 0;
        String temp = "";
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j].compareTo(arr[minPos]) < 0)
                    minPos = j;
                temp = arr[i];
                arr[i] = arr[minPos];
                arr[minPos] = temp;

                compCounter++;
            }
        }
        end = (System.nanoTime());

        System.out.println("Selection sort");
        System.out.println("Number of comparisons: " + compCounter);
        System.out.println("This took: " + ((end - start) / 1000) + "µs");
    }
}
