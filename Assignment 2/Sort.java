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

    //Selection sort algorithm
    public static void selectionSort(String[] arr) {
        //these two variables are used for determining the amount of time elapsed during the method execution
        long start = System.nanoTime();
        long end;
        //a counter to be used for counting each comparison within the sort.
        int compCounter = 0;
        //temporary sring used for switching element position in an array.
        String temp = "";
        //the sorting algorithm
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

        //Print statements including formatting
        System.out.println("\033[1mSelection Sort\033[0m");
        System.out.print("Number of comparisons: ");
        System.out.printf("%,7d %n", compCounter);
        System.out.printf("%-21s", "Time elapsed");
        System.out.print(": ");
        System.out.printf("%,7d", ((end - start) / 1000));
        System.out.println(" µs\n");
    }

    //Insertion sort
    public static void insertionSort(String[] arr) {
        //these two variables are used for determining the amount of time elapsed during the method execution
        long start = System.nanoTime();
        long end;
        //a counter to be used for counting each comparison within the sort.
        int compCounter = 0;
        //temporary sring used for switching element position in an array.
        String temp = "";
        //the sorting algorithm
        for (int i = 1; i < arr.length; i++) {
            String key = arr[i];
            int j;

            for (j = i - 1; j >= 0 && key.toUpperCase().compareTo(arr[j].toUpperCase()) < 0; j--) {
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

    //the initial method called when doing a merge sort. Recursively calls itself until all sub arrays are of size one, and then reverses
    //the calls through merge to create a fully sorted array
    public static void mergeSort(String[] arr, int firstIndex, int lastIndex, int[] counter) {
        if (firstIndex < lastIndex) {
            int midIndex = (firstIndex + lastIndex) / 2;
            //the new subarrays to be sorted recursively
            mergeSort(arr, firstIndex, midIndex, counter);
            mergeSort(arr, midIndex + 1, lastIndex, counter);
            //merges the arrays back together while sorting them
            merge(arr, firstIndex, midIndex, lastIndex, counter);
        }
    }
    
    //A seperate algorithm that takes in two subarrays and combines them while sorting them. This method is used recursively in mergeSort()
    //in order to divide and conquer
    private static void merge(String[] arr, int firstIndex, int midIndex, int lastIndex, int[] counter) {
        //variables to store the size of both subarrays
        int leftSize = midIndex - firstIndex + 1;
        int rightSize = lastIndex - midIndex;

        //creates temporary arrays as copies of the sub arrays within the passed array
        String[] arrLeft = new String[leftSize];
        String[] arrRight = new String[rightSize];

        //initializes the copy arrays
        for (int i = 0; i < leftSize; i++) {
            arrLeft[i] = arr[firstIndex + i];
        }
        for (int j = 0; j < rightSize; j++) {
            arrRight[j] = arr[midIndex + j + 1];
        }

        //variables to store the positions in the subarrays
        int i = 0;
        int j = 0;
        int k = firstIndex;

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
            counter[0]++;
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
    //initial call for quickSort. Recursively partitions and calls itself until the list is merged
    public static void quickSort(String[] arr, int firstIndex, int lastIndex, int[] counter) {
        if (firstIndex < lastIndex) {
            int pivot = partition(arr, firstIndex, lastIndex, counter);
            quickSort(arr, firstIndex, pivot-1, counter);
            quickSort(arr, pivot+1, lastIndex, counter);
        }
    }

    //the partition step of the sort. This is where the comparions and sorting occurs. Chooses a pivot and puts other elements on either side of it depending
    //on if it is lesser or greater.
    public static int partition(String[] arr, int firstIndex, int lastIndex, int[] counter) {
        String pivotVal = arr[lastIndex];
        int pivotLoc = firstIndex - 1;
        String tempStr = "";
        for (int i = firstIndex; i < lastIndex; i++) {
            if (arr[i].toUpperCase().compareTo(pivotVal.toUpperCase()) <= 0) {
                pivotLoc++;
                tempStr = arr[pivotLoc];
                arr[pivotLoc] = arr[i];
                arr[i] = tempStr;
            }
            counter[0]++;
        }
        tempStr = arr[pivotLoc + 1];
        arr[pivotLoc + 1] = arr[lastIndex];
        arr[lastIndex] = tempStr;

        return pivotLoc + 1;
    }
}

