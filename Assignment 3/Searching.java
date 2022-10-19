
/*
 * A class used to maintain static methods for searching algorithms (namely linear search and binary search)
 */
public class Searching {

    //sequentially searches the list for the key, returning the index of where it was found. If it was not found, returns -1.
    public static int linearSearch(String[] arr, String key) {
        int i = 0;
        while (i < arr.length && arr[i] != key) {
            i++;
        }
        if (i > )
        return i;
    }


    public static int binarySearch(String[] arr, int startIndex, int endIndex, String key) {
        int retVal = -1;
        int midIndex = (endIndex - startIndex) / 2;
        if (key == arr[midIndex]) {
            retVal = midIndex;
        } else if (key.compareTo(arr[midIndex]) < 0) {
            binarySearch(arr, startIndex, midIndex, key);
        } else {
            binarySearch(arr, midIndex + 1, endIndex, key);
        }
        return retVal;
    }
}
