package app.array.no136_singlenumber;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty array of integers, every element appears twice except for
 * one. Find that single one.
 * 
 * Note:
 * 
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 * 
 * Example 1:
 * 
 * Input: [2,2,1] Output: 1 Example 2:
 * 
 * Input: [4,1,2,1,2] Output: 4
 */
class Solution {
    // *Approach 2: Bit manipulation 
    public int singleNumber(int[] nums) {
        /**
         * XOR:
         * rule 1: a ^ a = 0 
         * rule 2: a ^ 0 = a
         * rule 3: a ^ b ^ c = a ^ c ^ b
         * rule 4: a ^ b ^ a = b ! 
         */
        int res = 0;

        // Do XOR to each number
        for (int num : nums) {
            res ^= num;
        }

        return res;
    }

    // Approach 1: HashSet tc: O(n) sc: O(n)
    public int singleNumber1(int[] nums) {
        // Special case: non- empty array
        // Use a varible to represent the single number
        Set<Integer> res = new HashSet<>();

        // Traverse the whole array to update res
        for (int i = 0; i < nums.length; i++) {
            if (!res.add(nums[i])) res.remove(nums[i]);
        }

        return res.iterator().next();
    }
}

public class SingleNumber {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[]{4,1,2,1,2};
        int res = s.singleNumber(nums);
        System.out.println(res);
    }
}