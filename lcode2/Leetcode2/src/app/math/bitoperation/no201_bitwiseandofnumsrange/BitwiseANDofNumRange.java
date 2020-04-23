package app.math.bitoperation.no201_bitwiseandofnumsrange;

/**
 Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

Example 1:

Input: [5,7]
Output: 4
Example 2:

Input: [0,1]
Output: 0
 */
class Solution {
    // approach 2: find the common prefix of the arr
    // In one word, this problem is asking us to find the common prefix of m and n 's binary code.
    public int rangeBitwiseAnd(int m, int n) {
        int leastPrefixBit = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            leastPrefixBit++;
        }
        return m << leastPrefixBit;
    }
    
    // approach 1: triviral solution
    public int rangeBitwiseAnd1(int m, int n) {
        if (m == n) return m;
        int res = m;
        // consider the overflow
        if (n == Integer.MAX_VALUE) n--;
        for (int i = m + 1; i <= n && res != 0; i++) {
            res &= i;
        }
        
        return res;
    }
}

public class BitwiseANDofNumRange {

}