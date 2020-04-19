package app.search.no33_rotatedbinarysearch;
/**
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

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
    public int search(int[] nums, int target) {
        /*
         * Analysis:
         * - The tricky point is when your search section contains rotated point, the target may appear on the both
         * - side (left-mid or mid-right).
         * - What we can do is to pick the continuous side (left-mid or mid-right) to do an inner binary search.
         * 
         */
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[left] > nums[right]) {
                // rotated side
                // ** must include nums[left] == nums[mid] (when left == mid, should search left)
                if (nums[left] <= nums[mid]) {
                    // left side is continous part, do the binary search
                    int leftRes = searchHelper(nums, target, left, mid);
                    if (leftRes != -1) {
                        return leftRes;
                    }
                    // left side no result, keep searching right part
                    left = mid + 1;
                } else {
                    // left side is continous part, do the binary search
                    int rightSearch = searchHelper(nums, target, mid, right);
                    if (rightSearch != -1) {
                        return rightSearch;
                    }
                    // if it doesn't appear on the right side. keep searching the left side.
                    right = mid - 1;
                }
            } else {
                return searchHelper(nums, target, left, right);
            }
        }
        
        return nums[left] == target ? left : - 1;
    }

    /**
     * search another side
     * @param nums original array
     * @param target search target
     * @param beginIndex search from 
     * @param endIndex search to (included)
     * @return the target index or -1
     */
    private int searchHelper(int[] nums, int target, int beginIndex, int endIndex) {
        int left = beginIndex, right = endIndex;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return nums[left] == target ? left : -1;
    }
}

public class RotatedBinarySearch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = new int[] {5,1,2,3,4};
        for (int target = 0; target < 10; target++) {

            int res =sol.search(nums, target);
            System.out.println(res);
        }
    }
}