package app.dp.no300_longestincsubstr;

import java.util.Arrays;

/**
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */

class Solution {
    // Approach 1: DP O(n^2)
    public int lengthOfLIS(int[] nums) {
        // Special case
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        // Initialize
        Arrays.fill(dp, 1);

        // Form the dp
        // dp[i] = max {dp[j] + 1} which nums[i] > nums[j]
        // dp[i] means the subseq ends with nums[i]
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            max = Math.max(dp[i], max);
        }

        return max;
    }
}

public class LongestIncSubstr {
    public static void main(String[] args) {
        int[] nums = new int[] {1,3,6,7,9,4,10,5,6};
        Solution s = new Solution();
        System.out.println(s.lengthOfLIS(nums));
    }
}