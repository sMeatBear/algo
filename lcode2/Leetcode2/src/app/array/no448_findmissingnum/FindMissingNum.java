package app.array.no448_findmissingnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some
 * elements appear twice and others appear once.
 * 
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * 
 * Could you do it without extra space and in O(n) runtime? You may assume the
 * returned list does not count as extra space.
 * 
 * Example:
 * 
 * Input: [4,3,2,7,8,2,3,1]
 * 
 * Output: [5,6]
 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // special case
        if (nums.length == 0) return res;

        Arrays.sort(nums);
        int num = 1;
        // compare the first element (to reduce if statement in the loop)
        if (num != nums[0]) res.add(num);
        num++;
        for (int i = 1; i < nums.length; i++) {
            // skip duplicate numebr
            if (nums[i] != nums[i - 1]) {
                // add all missing num in between
                while (num < nums[i]) {
                    res.add(num);
                    num++;
                }
                // next expected next
                num++;
            }
        }

        // special case like [1,1]
        // [1,1,2,2]
        // [2, 2]
        // handle remaining number
        if (num < nums.length || res.size() == 0) {
            for (; num <= nums.length; num++) {
                res.add(num);
            }
        }

        return res;
    }
}


public class FindMissingNum {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {4,3,2,7,7,2,3,1};
        List<Integer> res = s.findDisappearedNumbers(nums);
        System.out.println(res);
    }
}