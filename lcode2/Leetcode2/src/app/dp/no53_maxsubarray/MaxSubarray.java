package app.dp.no53_maxsubarray;
/**
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
 */

class Solution {
    // Approach 2: dp improved tc: O(n) sc: O(n)
    public int maxSubArray(int[] nums) {
        int max = nums[0], dp = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // update dp
            dp = dp > 0 ? dp + nums[i] : nums[i];
            max = max > dp ? max : dp;
        }
        
        return max;
    }
    
    // Approach 1: dp tc: O(n) sc: O(n)
    public int maxSubArray1(int[] nums) {
        // dp[i] = dp[i-1] + nums[i] when dp[i-1] > 0 otherwise nums[i] !
        // Use max to record the max subarray sum
        int max = nums[0];
        int len = nums.length;
        // dp table
        int[] dp = new int[len];
        
        // Fill the dp table
        dp[0] = nums[0];
        for (int i = 1; i < len; i++) {
            // Two condition
            if (dp[i - 1] < 0) dp[i] = nums[i];
            else dp[i] = dp[i - 1] + nums[i];
            // Update max
            max = max < dp[i] ? dp[i] : max;
        }
        
        return max;
    }
}

public class MaxSubarray {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        int max = s.maxSubArray(nums);
        System.out.println(max);
    }
}