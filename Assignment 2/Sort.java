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
        //these two variables are used for determining the amount of time elapsed during the method execution
        long start = System.nanoTime();
        long end;
        //a counter to be used for counting each comparison within the sort.
        int compCounter = 0;
        //temporary sring used for switching element position in an array.
        String temp = "";
        
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;
            for (int j = i + 1; j < arr.length; j++) {
                if ((arr[j].toUpperCase()).compareTo(arr[minPos].toUpperCase()) < 0)
                    minPos = j;
                temp = arr[i];
                arr[i] = arr[minPos];
                arr[minPos] = temp;

                compCounter++;
            }
        }
        end = (System.nanoTime());

        //small string necessary for formatting

        //Print statements including formatting
        System.out.println("\033[1mSelection Sort\033[0m");
        System.out.print("Number of comparisons: ");
        System.out.printf("%,7d %n", compCounter);
        System.out.printf("%-21s", "Time elapsed");
        System.out.print(": ");
        System.out.printf("%,7d", ((end - start) / 1000));
        System.out.println(" µs\n");
    }

    public static void insertionSort(String[] arr) {
        //these two variables are used for determining the amount of time elapsed during the method execution
        long start = System.nanoTime();
        long end;
        //a counter to be used for counting each comparison within the sort.
        int compCounter = 0;
        //temporary sring used for switching element position in an array.
        String temp = "";

        for (int i = 1; i < arr.length; i++) {
            String key = arr[i];
            int j;

            for (j = i - 1; j >= 0 && key.compareTo(arr[j]) < 0; j--) {
                arr[j + 1] = arr[j];
                compCounter++;
            }
            arr[j + 1] = key;
        }
        end = (System.nanoTime());

        System.out.println("\033[1mInsertion Sort\033[0m");
        System.out.print("Number of comparisons: ");
        System.out.printf("%,7d %n", compCounter);
        System.out.printf("%-21s", "Time elapsed");
        System.out.print(": ");
        System.out.printf("%,7d", ((end - start) / 1000));
        System.out.println(" µs\n");
    }
}
