/*
   Hash code tests and analytics.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Hashing {

    private static final int LINES_IN_FILE = 666;
    private static final int HASH_TABLE_SIZE = 250;
    private static LinkedList[] hashTable = new LinkedList[HASH_TABLE_SIZE];

    //Creates a hash code based on the ASCII values of all the characters in the String
    public static int makeHashCode(String str) {
        str = str.toUpperCase();
        int length = str.length();
        int letterTotal = 0;

        // Iterate over all letters in the string, totalling their ASCII values.
        for (int i = 0; i < length; i++) {
            char thisLetter = str.charAt(i);
            int thisValue = (int)thisLetter;
            letterTotal = letterTotal + thisValue;
        }

        // Scale letterTotal to fit in HASH_TABLE_SIZE.
        int hashCode = (letterTotal * 1) % HASH_TABLE_SIZE;  // % is the "mod" operator
        // TODO: Experiment with letterTotal * 2, 3, 5, 50, etc.

        return hashCode;
    }

    //puts String into the hash table that is stored in the class as a static variable. Uses makeHashCode() to create the hash.
    public static void put(String key) {
        int hash = makeHashCode(key);
        Node newNode = new Node(key);
        if (hashTable[hash] != null) {
            hashTable[hash].push(newNode);
        } else {
            hashTable[hash] = new LinkedList(newNode);
        }
    }

    //finds the String within the hash table based on the hash table and the String itself as a key.
    public static boolean get(int hash, String key, int[] counter) {
        boolean retFlag = false;
        Node listHead = hashTable[hash].getMyHead();
        while (listHead != null && !(retFlag)) {
            if (listHead.getMyString().compareTo(key) == 0) {
                retFlag = true;
            }
            counter[0]++;
            listHead = listHead.getMyNext();
        }
        return retFlag;
    }
}
