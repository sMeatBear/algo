package app.backtracking.no78_subsets;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the
 * power set).
 * 
 * Note: The solution set must not contain duplicate subsets.
 * 
 * Example:
 * 
 * Input: nums = [1,2,3] Output: [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2],
 * [] ]
 */

class Solution {
    // approach 1: backtrack
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> one = new ArrayList<>();
        res.add(one);
        backtrack(nums, res, one, 0);
        return res;
    }

    /**
     * @param contain if the element is contained in one list 
     * */
    public void backtrack(int[] nums, List<List<Integer>> res, List<Integer> one, int i) {
        for (; i < nums.length; i++) {
            one.add(nums[i]);
            res.add(new ArrayList<>(one));
            backtrack(nums, res, one, i + 1);
            // backtrack
            one.remove(one.size() - 1);
        }
    }
}

public class Subsets {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {1, 3, 9};
        System.out.println(s.subsets(nums));
    }
}