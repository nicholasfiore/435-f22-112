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
    
    //A seperate algorithm that takes in two subarrays and combines them while sorting them. This method is used recursively in mergeSort()
    //in order to divide and conquer
    private static void merge(String[] arr, int first, int middle, int last) {
        //variables to store the size of both subarrays
        int leftSize = middle - (first + 1);
        int rightSize = last - middle;

        //creates temporary arrays as copies of the sub arrays within the passed array
        String[] arrLeft = new String[leftSize];
        String[] arrRight = new String[rightSize];

        //initializes the copy arrays
        for (int i = 0; i < arrLeft.length; i++) {
            arrLeft[i] = arrLeft[first + i];
        }
        for (int i = 0; i < arrRight.length; i++) {
            arrRight[i] = arrRight[middle + 1 + i];
        }

        //variables to store the positions in the subarrays and original array
        int i = 0;
        int j = 0;
        int k = first;

        //Goes through both sub arrays, and places the earlier word/phrase in the original array at that position, until
        //one of subarrays reaches the end
        while (i < leftSize && j < rightSize) {
            if ((arrLeft[i].toUpperCase()).compareTo(arrRight[j].toUpperCase()) <= 0) {
                arr[k] = arrLeft[i];
                i++;
            } else {
                arr[k] = arrRight[j];
                j++;
            }
            k++;
        }

        //puts any remaining elements into the original array
        while (i < leftSize) {
            arr[k] = arrLeft[i];
            i++;
            k++;
        }

        while (j < rightSize) {
            arr[k] = arrRight[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(String[] arr, int first, int last) {
        int compCounter = 0;
        long start = System.nanoTime();
        long end;
        if (first > last) {
            int middle = first + (last - first) / 2;

            //the new subarrays to be sorted recursively
            mergeSort(arr, first, middle);
            mergeSort(arr, middle + 1, last);
            //merges the arrays back together while sorting them
            merge(arr, first, middle, last);
        }


        
        
    }

    
}

