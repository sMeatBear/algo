package app.linear.no15_3sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    // approach 2: no set
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // sort first to make deduplication easily
        Arrays.sort(nums);
        
        // fix one numebr first
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1, target = -nums[i];
            if (i == 0 || nums[i] != nums[i - 1]) {
                while (left < right) {
                    if (nums[left] + nums[right] == target) {
                        result.add(Arrays.asList(new Integer[] {nums[i], nums[left], nums[right]}));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {left++;}
                        while (left < right && nums[right] == nums[right + 1]) {right--;}
                    } else if (nums[left] + nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        
        return result;
    }

    // approach 1: hash set
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // sort first O(nlogn)
        Arrays.sort(nums);
        
        // O(n^2)
        for (int i = 0; i < nums.length; i++) {
            // skip the duplicate 
            if (i == 0 || nums[i] != nums[i - 1]) {
                Set<Integer> cache = new HashSet<>();
                if (i + 1 < nums.length) {cache.add(nums[i + 1]);}
                for (int j = i + 2; j < nums.length; j++) {
                    if (nums[j] != nums[j - 1]) {
                        int third = - nums[i] - nums[j];
                        if (cache.contains(third)) {
                            result.add(Arrays.asList(new Integer[] {nums[i], third, nums[j]}));
                        }
                        cache.add(nums[j]);
                    } else {
                        // *special case
                        if (nums[j] * 2 + nums[i] == 0) {
                            result.add(Arrays.asList(new Integer[] {nums[i], nums[j], nums[j]}));
                        }
                        // slide all the duplicate
                        for (; j < nums.length && nums[j] == nums[j - 1]; j++) {}
                        // cancel out the last j++
                        j--;
                    }
                }
            }
        }
        return result;
    }
}

public class ThreeSum {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // int[] nums = new int[] {-2,0,1,1,1,2,4,1,2,2,21,12,2,-1,-11,-13,1,1,1,1,1,1,1};
        int[] nums = new int[] {-2,0,1,1,2};
        System.out.println(sol.threeSum(nums));
    }   
}