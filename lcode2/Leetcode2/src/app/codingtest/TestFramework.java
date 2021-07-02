package app.codingtest;

class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int maxVal = 100000;
        // simulate a hashtable
        boolean[] set = new boolean[maxVal + 1];
        // add elem into the table
        for (int a : A) {
            // only add non-zero number
            if (a > 0) {
                set[a] = true;
            }
        }

        // traverse from 1 to maxVal to check false one
        for (int i = 1; i <= maxVal; i++) {
            if (!set[i]) {return i;}
        }

        // not found
        return -1;
    }
}

public class TestFramework {
    
}
