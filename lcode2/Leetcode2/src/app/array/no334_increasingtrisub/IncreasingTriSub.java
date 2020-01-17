package app.array.no334_increasingtrisub;

/**
 
Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false
 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        boolean exist = false;
        if (nums.length < 3) return false;

        // use two vars to keep the greatest number and the second biggest one
        int max = Integer.MAX_VALUE, secMax = Integer.MAX_VALUE;
        // We traverse the array and update max, secMax. If there exists a number greater than max, then return true
        for (int num : nums) {
            // !! Lower the max bound
            if (num <= secMax) {
                secMax = num;
            } else if (num <= max) {
                // must contain equal mark otherwise we will get same 3 val like 1, 1, 1
                max = num;
            } else return true;
        }
        
        return exist;
    }
}

public class IncreasingTriSub {
    public static void main(String[] args) {
        
    }
}