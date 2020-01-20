package app.search.binary.no33_searchinrotatedsortedarray;

/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
class Solution {
    // Approach 1: binary search
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        // 1. Get pivot index O(logn)
        int pivot = getPivot(nums);
        // 2. Check target belongs to which section
        int l = 0, r = nums.length - 1;
        if (target == nums[r]) return r;
        else if (target < nums[r]) l = pivot;
        else r = pivot - 1;
        // 3. Binary Search O(logn)
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] < target) l = m + 1;
            else if (nums[m] > target) r = m - 1;
            else return m;
        }

        return -1;
    }

    // search pivot
    public int getPivot(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[r] < nums[m]) l = m + 1;
            else r = m;
        }

        return l;
    }
}

public class SearchRotatedSortedArr {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {4,5,6,7,0,1,2};
        int p = s.getPivot(nums);
        System.out.println(p);
    }
}