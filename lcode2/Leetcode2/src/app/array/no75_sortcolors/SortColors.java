package app.array.no75_sortcolors;

import java.util.Arrays;

/**
Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?
 */
class Solution {
    // approach 1: use three points
    public void sortColors(int[] nums) {
        // l : from left to right, r : from right to left
        // If nums[l] is 0, l++. If nums[r] is 2, r--
        int l = 0, r = nums.length - 1, i = l;
        while (i < r) {
            while (l < nums.length && nums[l] == 0 ) l++;
            while (r > 0 && nums[r] == 2 ) r--;
            i = l;
            if (i > r) break;
            while (i < r && nums[i] == 1) i++;
            if (nums[i] == 0) {
                nums[i] = nums[l];
                nums[l] = 0;
                l++;
            } else if (nums[i] == 2){
                nums[i] = nums[r];
                nums[r] = 2;
                r--;
            }
        }
    }
}

public class SortColors {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {0,2};
        s.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}