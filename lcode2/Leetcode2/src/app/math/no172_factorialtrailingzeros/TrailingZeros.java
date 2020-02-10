package app.math.no172_factorialtrailingzeros;

/**
Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
 */
class Solution {
    /**
     * This question is pretty straightforward.

        Because all trailing 0 is from factors 5 * 2.

        But sometimes one number may have several 5 factors, for example, 25 have two 5 factors, 125 have three 5 factors. In the n! operation, factors 2 is always ample. 

        **So we just count how many 5 factors in all number from 1 to n.**

     */
    public int trailingZeroes(int n) {
        /**
         * I think the key idea is count how many 5's are in the factorial.
            So first we add n/5.
            Wait, we are missing 5X5, 2X5X5..., so we add n/25 (why not count as two 5's for each , because one is already counted in n/5).
            Wait, we are missing 5X5X5, 2X5X5X5..., so we add n/125.
            Thus, count = n/5 + n/25 + n/125 + ... + 0
         */
        // Find all combination of 5^n * m
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}

public class TrailingZeros {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = Integer.MAX_VALUE;
        int res = s.trailingZeroes(n);
        System.out.println(res);
    }
}