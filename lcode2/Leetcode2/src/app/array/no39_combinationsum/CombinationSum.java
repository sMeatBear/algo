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
    // approach 1 dp:
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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