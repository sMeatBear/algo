package app.math.no29_dividetwoint;

/**
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
 */
class Solution {
    // Approach 2: optimized bf
    public int divide(int dvd, int dvr) {
       // If the sign of diviend and divisor are the same
       int sign = 1;
       // Prevent overflow
       long dividend = (long)dvd, divisor = (long)dvr;
       if (dividend * divisor < 0) sign = -1;
       if (dividend * divisor == 0) return 0;

       dividend = Math.abs(dividend);
       divisor = Math.abs(divisor);
       
       // loop
       long quotient = 1, totalQ = 1;
        long subsum = divisor, sum = subsum;

        while (quotient > 0) {
            if (subsum + sum <= dividend) {
                // Add to sum
                sum = subsum + sum;
                totalQ += quotient;
                // Update subsum
                subsum += subsum;
                // Update quotient step
                quotient += quotient;
            } else {
                // reset quotient step
                quotient /= 2;
                subsum /= 2;
            }
        }

        // Handle quotient = 0
        if (totalQ == 1 && dividend < divisor) {
            return 0;
        }

        if (sign == -1) {
            return (int)(-totalQ);
        } else {
            return (totalQ > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)totalQ);
        }
    }

    // Approach 1: bf
    public int divide1(int dvd, int dvr) {
        // If the sign of diviend and divisor are the same
        boolean minus = false;
        // Prevent overflow
        long dividend = (long)dvd, divisor = (long)dvr;
        if (dividend * divisor < 0) minus = true;
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        
        // loop
        int quotient = 0;
        long sum = 0;
        for (; sum < dividend; quotient++) {
            sum += divisor;
        }
        quotient = quotient < 0 ? Integer.MAX_VALUE : quotient;
        if (sum > dividend) quotient--;

        if (minus) return -quotient;
        return quotient;
    }
}

public class DivideTwoInt {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int q = sol.divide(-2147483648, -1);
        System.out.println(q);
    }
}