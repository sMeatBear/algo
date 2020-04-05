package app.array.no283_movezeros;

/**
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 */
class Solution {
    // Approach 2: Improve at most *(4 conditional statments)*
    public void moveZeroes(int[] nums) {
        int len = nums.length;
        // Use one pointer to record the position of current zero
        int p0 = 0, i = 1;
        
        while (p0 < len && i < len) {
            if (nums[p0] == 0) {
                if (nums[i] != 0) {
                    // Swap
                    nums[p0] = nums[i];
                    nums[i] = 0;
                } else {
                    i++;
                }
            } else {
                i++;
                p0++;
            }
        }
    }
    
    // Approach 1: Two pointers (at most 8 conditional statements) !!
    public void moveZeroes2(int[] nums) {
        int len = nums.length;
        // Use one pointer to record the position of current zero
        int p0 = 0, i = 1;
        
        while (p0 < len && i < len) {
            // Find the next zero element position
            while (p0 < len && nums[p0] != 0) p0++; // This is a repeat statement with the previous one
            
            if (p0 < len) {
                // Find the next non-zero element position
                i = p0 + 1;
                while (i < len && nums[i] == 0) i++;
                if (i < len) {
                    // Swap
                    nums[p0] = nums[i];
                    nums[i] = 0;
                    // zero pointer move to next position
                }
            }
        }
    }
}

public class MoveZeroes {
    public static void main(String[] args) {
        System.out.println("test");
    }
}