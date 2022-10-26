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

    public static int put(String key) {
        int hash = makeHashCode(key);
        Node newNode = new Node(key);
        if (hashTable[hash] != null) {
            hashTable[hash].push(newNode);
        } else {
            hashTable[hash] = new LinkedList(newNode);
        }
        return hash;
    }

    public static boolean get(int hash, String key) {
        boolean retFlag = false;
        boolean loopFlag = true;
        Node listHead = hashTable[hash].getMyHead();
        while (listHead.getMyNext() != null && loopFlag) {
            if (listHead.getMyString().compareTo(key) == 0) {
                retFlag = true;
                loopFlag = false;
            }
            listHead = listHead.getMyNext();
        }
        return retFlag;
    }
}
