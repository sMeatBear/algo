package app.array.no448_findmissingnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    // approach 2 : hashset
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> all = new HashSet<>();
        // add all nums to hashset
        for (int i = 0; i < nums.length; i++) {
            all.add(nums[i]);
        }

        // check one by one
        for (int i = 1; i <= nums.length; i++) {
            if (!all.contains(i)) {
                res.add(i);
            }
        }

        return res;
    }
    // approach 1 : sorting
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> res = new ArrayList<>();
        // special case
        if (nums.length == 0) return res;

        Arrays.sort(nums);
        int num = 1;
        for (int i = 0; i < nums.length; i++) {
            // skip duplicate numebr
            if (i == 0 || nums[i] != nums[i - 1]) {
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
        for (; num <= nums.length; num++) {
            res.add(num);
        }

        return res;
    }
}


public class FindMissingNum {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {1,1};
        List<Integer> res = s.findDisappearedNumbers(nums);
        System.out.println(res);
    }
}