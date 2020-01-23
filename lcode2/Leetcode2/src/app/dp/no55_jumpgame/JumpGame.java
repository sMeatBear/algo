package app.dp.no55_jumpgame;

/**
 Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.
 */

class Solution {
    // Approach 1: 
    public boolean canJump(int[] nums) {
        // Record current max jump pace
        // one move max--
        int max = 0;
        // Loop except the last one
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, nums[i]);
            // From start to current, if the max remaining jump pace is 0, then return false.
            if (max == 0) return false;
            // Update max remaining jump pace
            max--;
        }

        return true;
    }
}

public class JumpGame {
    public static void main(String[] args) {
        Solution s= new Solution();
        int[] nums = new int[] {2,3,1,1,4};
        boolean res = s.canJump(nums);
        System.out.println(res);
    }
}