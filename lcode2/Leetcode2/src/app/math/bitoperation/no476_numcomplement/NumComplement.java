package app.math.bitoperation.no476_numcomplement;
/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.

 

Example 1:

Input: 5
Output: 2
Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to output 2.
 

Example 2:

Input: 1
Output: 0
Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So you need to output 0.
 

Note:

The given integer is guaranteed to fit within the range of a 32-bit signed integer.
You could assume no leading zero bit in the integer’s binary representation.
This question is the same as 1009: https://leetcode.com/problems/complement-of-base-10-integer/
 */
class Solution {
    // Approach 2: no loop
    public int findComplement(int num) {
        // highestOneBit returns the value with leftmost 1 in given number followed by all zero
        // << 1 then - 1 means all 1 from leftmost 1 to the rightmost
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        return ~num & mask;
    }

    // Approach 1: XOR
    public int findComplement1(int num) {
        // use XOR, POSITIVE
        int res = num;
        int bound = 0;
        
        // find the msb and do the xor bitwise
        for (int offset = 0; num >> offset != bound; offset++) {
            int mask = 1 << offset;
            res ^= mask;
        }
        
        return res;
    }
}

public class NumComplement {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findComplement(5));
        System.out.println(Integer.highestOneBit(5));
    }
}