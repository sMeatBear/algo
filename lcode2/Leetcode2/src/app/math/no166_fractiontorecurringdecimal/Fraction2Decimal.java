package app.math.no166_fractiontorecurringdecimal;

import java.util.HashMap;
import java.util.Map;

/**
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"
 */
class Solution {
    public String fractionToDecimal(int numer, int denom) {
        StringBuilder res = new StringBuilder();

        // Simulate the division process
        // ** Mind overflow **
        long numerator = numer, denominator = denom;
        // ** Count the sign only once, then let the numerator and denominator to be positive **
        if (numerator * denominator < 0) res.append('-');
        numerator = Math.abs(numerator);
        denominator = Math.abs(denominator);
        // The left part of the decimal mark
        res.append(numerator / denominator);
        long mod = numerator % denominator;
        if (mod != 0) res.append('.');
        // ** Record the one decimal place position in the string
        int pos = res.length();
        // Handle right part of the decimal mark
        // ** Record the previous mod value to judge if there is a recurring. **
        Map<Long, Integer> prevs = new HashMap<>();
        prevs.put(mod, pos++);

        while (mod != 0) {
            // 1. Divide
            long dividend = mod * 10;
            res.append(dividend / denominator);
            // 2. Next mod
            mod = dividend % denominator;
            // Check if recurring
            if (prevs.containsKey(mod)) {
                int offset = prevs.get(mod);
                res.insert(offset, '(');
                res.append(')');
                break;
            }

            // 3. Record
            prevs.put(mod, pos++);
        }

        return res.toString();
    }
}

public class Fraction2Decimal {
    public static void main(String[] args) {
        Solution s = new Solution();
        int numerator = -1, denominator = -2147483648;
        String res = s.fractionToDecimal(numerator, denominator);
        System.out.println(-19 % -4);
        System.out.println(res);
    }
}