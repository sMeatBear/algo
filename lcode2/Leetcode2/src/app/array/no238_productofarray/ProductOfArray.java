package app.array.no238_productofarray;

import java.util.Arrays;

/**
Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Constraint: It's guaranteed that the product of the elements of any prefix or suffix of the array (including the whole array) fits in a 32 bit integer.

Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
 */

class Solution {
    // Approach 3: improvement of approach2, reduce the space consumption !
    // tc: O(n) sc: O(1) (except the new result array)
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        // Use l and r instead of two arrays
        int l = 1, r = 1;
        // Create the result array
        int[] res = new int[len];
        // In case when compute res[0] from the right, it won't end up with 0 !!
        res[0] = 1;
        
        // Compute left part, except res[0], l represents l[i-1]
        for (int i = 1; i < len; i++) {
            // Update l
            l *= nums[i - 1];
            res[i] = l;
        }
        // Compute right part, except res[len - 1], r represents r[i+1]
        for (int i = len - 2; i >= 0; i--) {
            // Update r
            r *= nums[i + 1];
            res[i] *= r;
        }
        
        return res;
    }
    
    // Approach 2: without division, use l and r array
    // tc: O(n), sc: O(n)
    public int[] productExceptSelf2(int[] nums) {
        /*
            Use l array and r array to record the product of elements to the left of i and right of i
            then do the muplication
        */
        int len = nums.length;
        int[] l = new int[len], r = new int[len];
        
        // Fill the l array
        l[0] = 1;
        for (int i = 1; i < len; i++) {
            l[i] = l[i - 1] * nums[i - 1];
        }
        // Fill the r array
        r[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            r[i] = r[i + 1] * nums[i + 1];
        }
        
        // Compute the new array
        int[] res = new int[len];
        for (int i = 0; i < len; i++) {
            res[i] = l[i] * r[i];
        }
        
        return res;
    }
    
    // Approach 1: with division tc: O(n) sc: O(n)
    public int[] productExceptSelf1(int[] nums) {
        // Compute the total product
        int prod = 1;
        /*
            Count zero !:
            1. If there is no zero then divide product by each elem in the array
            2. If there is 1 zero then only compute the elem equal to 0 in the new array
            3. If there is more than 1 zero then return new array with all 0s
        */
        int count = 0, idx = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0)
                prod *= nums[i];
            else {
                count++;
                // Record the index of 0 elem
                idx = i;
            }
        }
        
        // Divide total product by each number
        int[] res = new int[nums.length];
        
        // Condition 1:
        if (count == 0)
            for (int i = 0; i < nums.length; i++) res[i] = prod / nums[i];
        else if (count == 1)
            // Condition 2:
            res[idx] = prod;
        // Condition 3: do nothing
        
        return res;
    }
}

public class ProductOfArray {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {1, 2, 4, 8};
        int[] newNums = s.productExceptSelf(nums);
        System.out.println(Arrays.toString(newNums));
    }
} 