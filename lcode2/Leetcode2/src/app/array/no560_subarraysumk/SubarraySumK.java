package app.array.no560_subarraysumk;

import java.util.HashMap;
import java.util.Map;


/**
Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */
class Solution {
    // Approach 3: Hashmap tc: O(n) improvement of Approach2 **
    public int subarraySum(int[] nums, int k) {
        int len = nums.length;
        int count = 0;
        // <sum, freq>
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(sum, 1);
        // Compute each stage's sum
        // Each corresponding result moves rightforward 1 step
        // Goal: sub[i : j] = sum[j] - sum[i-1] = k  =>  sum[j] - k= sum[i-1] !!! (can make sure it always meet len > j > i)
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            // Find the occurence of sum[i]
            count += map.getOrDefault(sum - k, 0);
            // Update sum map
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return count;        
    }
    
    // Approach 2: Math accummulative sum tc: O(n^2), O(n)
    public int subarraySum2(int[] nums, int k) {
        int len = nums.length;
        int[] sum = new int[len + 1];
        int count = 0;
        
        // Compute each stag's sum
        // Use index 0 as 0 !!!
        sum[0] = 0;
        for (int i = 0; i < len; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        
        // realSum[i:j] = sum[j + 1] - sum[i]
        // 0 <= i < j < len + 1
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len + 1; j++) {
                if(sum[j] - sum[i] == k) count++;
            }
        }
        
        return count;
    }
    
    // Approach 1: BF O(n^2)
    public int subarraySum1(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        int count = 0;
        
        for (int i = 0; i < len; i++) {
            sum = nums[i];
            if (sum == k) count++;
            for (int j = i + 1; j < len; j++) {
                sum += nums[j];
                if (sum == k) count++;
            }
        }
        
        return count;
    }
}

public class SubarraySumK {
    public static void main(String[] args) {
        
    }
}