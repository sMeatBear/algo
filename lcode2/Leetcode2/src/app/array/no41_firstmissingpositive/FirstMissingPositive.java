package app.array.no41_firstmissingpositive;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.
 */
class Solution {
    // approach 2: 
    // approach 1: set
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
           set.add(nums[i]); 
        }
        
        int i = 1;
        for (; i <= nums.length; i++) {
            if (!set.contains(i)) return i;
        }
        
        return i;
    }
}


public class FirstMissingPositive {
    public static void main(String[] args) {
        
    }
}