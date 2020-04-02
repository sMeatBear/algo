package app.linear.no202_happynumber;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 * 
 * A happy number is a number defined by the following process: Starting with
 * any positive integer, replace the number by the sum of the squares of its
 * digits, and repeat the process until the number equals 1 (where it will
 * stay), or it loops endlessly in a cycle which does not include 1. Those
 * numbers for which this process ends in 1 are happy numbers.
 * 
 * Example:
 * 
 * Input: 19 Output: true Explanation: 12 + 92 = 82 82 + 22 = 68 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */

class Solution {
    public boolean isHappy(int n) {
        // Use hash set to record the repeat sum
        Set<Integer> resSet = new HashSet<>();
        
        // Do the loop
        while (n != 1) {
            // Compute the sum of each digit's square
            int newN = 0;
            while (n != 0) {
                int mod = n % 10;
                newN += mod * mod;
                n /= 10;
            }
            
            // Check is there a repeat
            if (!resSet.add(newN)) return false;
            // Update n
            n = newN;
        }
        
        return true;
    }
}

public class HappyNumber {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 19;
        boolean res = s.isHappy(n);
        System.out.println(res);
    }
}