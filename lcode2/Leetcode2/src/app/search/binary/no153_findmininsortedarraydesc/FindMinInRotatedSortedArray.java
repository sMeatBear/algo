package app.search.binary.no153_findmininsortedarraydesc;

/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2] 
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0
 */
class Solution {
    // approach 1: binary search
    public int findMin(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (nums[l] < nums[mid]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return r;    
    }

    // practice the template
    public int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        // binary search: each iteration's step is 1
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }

        return l;
    }
}

public class FindMinInRotatedSortedArray {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {1, 3, 9, 11, 32,111, 890};
        System.out.println(s.binarySearch(nums, 111));
    }
}