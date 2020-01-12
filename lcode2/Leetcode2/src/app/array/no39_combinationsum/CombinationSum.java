package app.array.no39_combinationsum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
     Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

    The same repeated number may be chosen from candidates unlimited number of times.

    Note:

    All numbers (including target) will be positive integers.
    The solution set must not contain duplicate combinations.
    Example 1:

    Input: candidates = [2,3,6,7], target = 7,
    A solution set is:
    [
    [7],
    [2,2,3]
    ]
    Example 2:

    Input: candidates = [2,3,5], target = 8,
    A solution set is:
    [
    [2,2,2,2],
    [2,3,3],
    [3,5]
    ]

*/
class Solution {
    // approach 4: optimized dp
    public List<List<Integer>> combinationSum4(int[] cands, int t) {
        Arrays.sort(cands); // sort candidates to try them in asc order
        List<List<List<Integer>>> dp = new ArrayList<>();
        for (int i = 1; i <= t; i++) { // run through all targets from 1 to t
            List<List<Integer>> newList = new ArrayList<>(); // combs for curr i
            // run through all candidates <= i
            for (int j = 0; j < cands.length && cands[j] <= i; j++) {
                // special case when curr target is equal to curr candidate
                if (i == cands[j]) newList.add(Arrays.asList(cands[j]));
                // if current candidate is less than the target use prev results
                else for (List<Integer> l : dp.get(i-cands[j]-1)) {
                    // Deduplicate, insert by ascending order
                    if (cands[j] <= l.get(0)) {
                        List<Integer> cl = new ArrayList<>();
                        cl.add(cands[j]); cl.addAll(l);
                        newList.add(cl);
                    }
                }
            }
            dp.add(newList);
        }
        return dp.get(t-1);
    }


    // approch 3: backtracking
    private List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        dfs(candidates, target, 0, 0, new ArrayList<>());
        return res;
    }
    
    private void dfs(int[] can, int target, int sum, int start, List<Integer> sol) {
        if (sum == target) {
            res.add(new ArrayList<>(sol));
            return;
        }
        
        for (int i = start; i < can.length; i++) {
            //put the ending case here rather than the beginning of dfs to avoid redundant recursion
            if (sum + can[i] > target) {
                continue;
            }
            
            sol.add(can[i]);
            dfs(can, target, sum + can[i], i, sol);
            sol.remove(sol.size() - 1);
        }
    }

    // approach 2 backtracking:
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }
    
    /**
     * @param templist 
     */
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        // !! end try (need to use a new list because we need to change tempList )
        if (remain > 0) { 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                // backtrack
                tempList.remove(tempList.size() - 1);
            }
        }

        list.add(new ArrayList<>(tempList));
    }

    // approach 1 dp:
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates.length == 0) return res;

        // sort
        Arrays.sort(candidates);

        int min = candidates[0];
        // !! store intermidiate results
        Map<Integer, Set<List<Integer>>> tables = new HashMap<>();
        // intialize
        List<Integer> r = new ArrayList<>();
        Set<List<Integer>> s = new HashSet<>();
        r.add(min);
        s.add(r);
        tables.put(min, s);

        // dp
        for (int i = min + 1; i <= target; i++) {
            // if there exists i in candidates
            Set<List<Integer>> newSet = new HashSet<>();
            if (binarySearch(candidates, i)) {
                List<Integer> newRes = new ArrayList<>();
                newRes.add(i);
                newSet.add(newRes);
                tables.put(i, newSet);
            }
            int lo = 1, hi = i - 1;
            while (lo <= hi) {
                if (tables.containsKey(lo) && tables.containsKey(hi)) {
                    Set<List<Integer>> loSet = tables.get(lo), hiSet = tables.get(hi);
                    for (List<Integer> lolist : loSet) {
                        for (List<Integer> hilist : hiSet) {
                            List<Integer> newRes = new ArrayList<>();
                            newRes.addAll(lolist);
                            newRes.addAll(hilist);
                            // sort and de-duplicate
                            Collections.sort(newRes);
                            newSet.add(newRes);
                        }
                    }
                    tables.put(i, newSet);
                }
                lo++;
                hi--;
            }
        }

        if (tables.containsKey(target)) {
            res = new ArrayList<>(tables.get(target));
        }

        return res;
    }

    public boolean binarySearch(int[] candidate, int target) {
        if (candidate.length == 0) return false;
        int l = 0, r = candidate.length - 1;

        while (l < r) {
            int mid = (l + r) / 2;
            if (candidate[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (candidate[l] == target) return true;
        return false;
    }
}


public class CombinationSum {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] candidate = new int[] {2,3,5}; 
        int target = 8;
        System.out.println(s.combinationSum(candidate, target));
    }
}