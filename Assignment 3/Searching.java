
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
        if (i >= arr.length)
            i = -1;
        return i;
    }

    //recursively searches the list by comparing the key to the item at the middle of the list, then choosing half of the array to
    //then search depending on whether the key is lesser or greater than the element at the middle. If the element is found, returns
    //the index, otherwise returns -1.
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