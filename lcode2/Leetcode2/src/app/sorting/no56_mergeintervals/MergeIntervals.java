package app.sorting.no56_mergeintervals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 */
class Solution {
    // Approach 2: sorting improve
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return new int[0][];
        List<int[]> resList = new ArrayList<>();
        
        // Sort by the first dimension value O(nlogn)
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        // A pointer to follow the last added result
        int[] p = intervals[0];
        // Add first interval as a initial state
        resList.add(p);
        for (int[] itv : intervals) {
            // There is overlap between previous interval and current one.
            if (itv[0] <= p[1] && p[1] < itv[1]) p[1] = itv[1];
            else if (itv[0] > p[1]) {
                // A new interval
                resList.add(itv);
                // Change the pointer to current interval
                p = itv;
            }
        }

        return resList.toArray(new int[resList.size()][]);
    }

    // Approach 1: Sort + Stack O(nlogn)
    public int[][] merge1(int[][] intervals) {
        List<int[]> resList = new ArrayList<>();
        
        // Sort by the first dimension value O(nlogn)
        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
        
        // O(n) traverse
        Deque<int[]> stack = new ArrayDeque<>();
        for (int[] inv : intervals) {
            if (stack.isEmpty()) {
                stack.push(inv);
            } else {
                int[] cur = stack.peek();
                if (inv[0] <= cur[1] && inv[1] > cur[1]) {
                    // Combine
                    stack.pop();
                    stack.push(new int[] {cur[0], inv[1]});
                } else if (inv[0] > cur[1]) {
                    // New interval
                    resList.add(stack.pop());
                    stack.push(inv);
                }
            }
        }

        // Handle the rest
        while (!stack.isEmpty()) {
            resList.add(stack.pop());
        }

        // Convert list to array
        int[][] res = new int[resList.size()][2];
        for (int i = 0 ; i < res.length; i++) res[i] = resList.get(i);

        return res;
    }
}

public class MergeIntervals {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = s.merge(intervals);
        System.out.println(Arrays.deepToString(res));
    }
}