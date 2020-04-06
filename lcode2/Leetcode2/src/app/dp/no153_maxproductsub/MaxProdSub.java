package app.dp.no153_maxproductsub;

/**
Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
 */
class Solution {
    // Approach 2: DP record max and min tc: O(n) sc: O(1)
    public int maxProduct(int[] nums) {
        int max = nums[0], min = max, res = max;
        int len = nums.length;
        
        for (int i = 1; i < len; i++) {
            int prod1 = max * nums[i], prod2 = min * nums[i];
            
            // Update max
            max = prod1;
            max = prod2 > max ? prod2 : max;
            max = nums[i] > max ? nums[i] : max;
            res = res > max ? res : max;
            // Update min
            min = prod1;
            min = prod2 < min ? prod2 : min;
            min = nums[i] < min ? nums[i] : min;
        }
        
        return res;
    }
    
    // Approach 1: BF tc O(n^2)
    public int maxProduct1(int[] nums) {
        int max = nums[0], len = nums.length, prod = max;
        
        for (int i = 0; i < len; i++) {
            prod = nums[i];
            max = prod > max ? nums[i] : max;
            for (int j = i + 1; j < len; j++) {
                max = (prod *= nums[j]) > max ? prod : max;
            }
        }
        
        return max;
    }
}

public class MaxProdSub {

}