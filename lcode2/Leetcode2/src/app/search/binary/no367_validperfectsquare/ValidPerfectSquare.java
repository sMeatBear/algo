package app.search.binary.no367_validperfectsquare;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false
 */
class Solution {
    // approach 1: binary search
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        
        // use binary search
        int left = 0, right = num;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long midSquare = (long) mid * mid;
            if (midSquare < num) {
                left = mid + 1;
            } else if (midSquare > num){
                right = mid - 1;
            } else {
                return true;
            }
        }
        
        return false;
    }
}

public class ValidPerfectSquare {
    
}