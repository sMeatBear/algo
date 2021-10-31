package app.string.basic;

import java.util.Arrays;

/**
 * Test the different way to write getNext method
 */
public class KMPTest {
    /** way 1 */
    public static int[] getNext1(char[] t) {
        int len = t.length;
        int[] next = new int[len];
        next[0] = -1;

        // backtrack when mismatch
        int i = 0, j = -1;
        while (i < len - 1) {
            if (j == -1 || t[i] == t[j]) {
                i++;
                j++;
                // get the i + 1's next value
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /** Way 2 */
    public static int[] getNext2(char[] t) {
        int len = t.length;
        int[] next = new int[len];
        next[0] = -1;

        for (int i = 1; i < len; i++) {
            int temp = i - 1, j = next[temp];
            while (j >= 0 && t[j] != t[temp]) {
                j = next[j];
            }
            next[i] = j + 1;
        }

        return next;
    }

    public static void main(String[] args) {
        // test
        // abababb
        char[] t = new String("abacccaaccddabacababa").toCharArray();
        System.out.println(Arrays.toString(getNext1(t)));
        System.out.println(Arrays.toString(getNext2(t)));
    }
}