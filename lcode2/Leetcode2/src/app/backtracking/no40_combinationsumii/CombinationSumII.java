package app.backtracking.no40_combinationsumii;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    // approach 1: backtracking
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // sort first
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, res, new ArrayList<>(), 0, 0, target);

        return res;
    }
    
    public void backtrack(int[] can, List<List<Integer>> res, List<Integer> tmp, int index, int sum, int target) {
        for (int i = index; i < can.length; i++) {
            // !! no duplicate candidate
            if (i != index && can[i] == can[i - 1]) continue;

            int tmpSum = sum + can[i];
            if (tmpSum < target) {
                tmp.add(can[i]);
                // try to add next candidate
                backtrack(can, res, tmp, i + 1, tmpSum, target);
                // backtrack
                tmp.remove(tmp.size() - 1);
            } else if (tmpSum == target){
                // end condition
                List<Integer> newList = new ArrayList<>(tmp);
                newList.add(can[i]);
                // add new result
                res.add(newList);
            } else {
                break;
            }
            
        }
    }

    // original
    // approach 1: backtracking
    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        // sort first
        Arrays.sort(candidates);
        Set<List<Integer>> res = new HashSet<>();
        backtrack(candidates, res, new ArrayList<>(), 0, 0, target);

        return new ArrayList<>(res);
    }
    
    public void backtrack(int[] can, Set<List<Integer>> res, List<Integer> tmp, int index, int sum, int target) {
        for (; index < can.length && can[index] <= target; index++) {
            tmp.add(can[index]);
            int tmpSum = sum + can[index];
            if ( tmpSum < target) {
                // try to add next candidate
                backtrack(can, res, tmp, index + 1, tmpSum, target);
            } else if (tmpSum == target) {
                // add an solution
                res.add(new ArrayList<>(tmp));
            }
            // backtrack
            tmp.remove(tmp.size() - 1);
        }
    }
} 


public class CombinationSumII {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] candidates = new int[] {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(s.combinationSum2(candidates, target));
    }
}