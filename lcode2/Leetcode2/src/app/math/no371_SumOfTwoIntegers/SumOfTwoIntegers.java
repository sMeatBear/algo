package app.math.no371_SumOfTwoIntegers;

/**
Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1
 */
class Solution {
    // Approach 1: use bit operation
    public int getSum(int a, int b) {
        // Only 1 meets 1 will have carry.
        int carry = (a & b) << 1;
        // Simulate plus without carry (0+1=1,0+0|1+1=0)
        int sum = a ^ b;

        // Loop until no carry
        while (carry != 0) {
            a = sum;
            b = carry;
            sum = a ^ b;
            carry = (a & b) << 1;
        }

        return sum;
    }
}

public class SumOfTwoIntegers {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.getSum(-1, 3));
    }
}