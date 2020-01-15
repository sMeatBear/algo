package app.array.no41_firstmissingpositive;

import java.util.Arrays;
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
    // Approach 2: Greedy traverse the nums to place each num in the right place if it's possible
    public int firstMissingPositive(int[] nums) {
        // Greedy
        for (int i = 0; i < nums.length; i++) {
            int index = nums[i] - 1;
            if (index >= 0 && index < nums.length && nums[index] != nums[i]) {
                int tmp = nums[index];
                nums[index] = nums[i];
                nums[i] = tmp;
                // place the num on this index rightly or cannot find the right one 
                // then move to next number
                i--;
            }
        }

        // return first misplace num's index + 1
        int i = 0;
        for (; i < nums.length; i++) {
            int val = i + 1;
            if (nums[i] != val) return val;
        }

        return i + 1;
    }
    // approach 1: set
    public int firstMissingPositive1(int[] nums) {
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
        int[] nums = new int[] {3,4,-1,1};
        Solution s = new Solution();
        int res = s.firstMissingPositive(nums);
        System.out.println(res);
    }
}