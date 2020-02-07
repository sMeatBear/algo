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
    // **Approach 2: DP optimized O(nlogn)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // dp array: Only record different length subseq's last element
        // 1. Use index to represent the length of subseq
        // 2. The content is the last element of the subseq with specific length
        int[] dp = new int[nums.length];
        dp[0] = Integer.MAX_VALUE;
        int len = 0;

        for (int num : nums) {
            // Find where to replace or append the smaller element to maximize the subseq's length
            int i = binarySearch(dp, len, num);
            dp[i] = num;
            if (i > len) len = i;
        }

        return len + 1;
    }

    public int binarySearch(int[] dp, int end, int target) {
        int l = 0, r = end;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (dp[mid] < target) l = mid + 1;
            else r = mid - 1;
        }

        // Same element insert into the same spot, not found element insert the higher position
        return l;
    }

    // Approach 1: DP O(n^2)
    public int lengthOfLIS1(int[] nums) {
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
        int[] nums = new int[] {10,9,2,5,3,7,101,18};
        Solution s = new Solution();
        System.out.println(s.lengthOfLIS(nums));

    }
}