package app.backtracking.no1035_uncrosselines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

/**
 * We write the integers of A and B (in the order they are given) on two separate horizontal lines.

Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:

A[i] == B[j];
The line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.

Return the maximum number of connecting lines we can draw in this way.

 

Example 1:


Input: A = [1,4,2], B = [1,2,4]
Output: 2
Explanation: We can draw 2 uncrossed lines as in the diagram.
We cannot draw 3 uncrossed lines, because the line from A[1]=4 to B[2]=4 will intersect the line from A[2]=2 to B[1]=2.
Example 2:

Input: A = [2,5,1,2,5], B = [10,5,2,1,5,2]
Output: 3
Example 3:

Input: A = [1,3,7,1,7,5], B = [1,9,2,5,1]
Output: 2
 

Note:

1 <= A.length <= 500
1 <= B.length <= 500
1 <= A[i], B[i] <= 2000
 */
class Solution {
    // approach 2: same as Longest Common Sequence
    public int maxUncrossedLines(int[] A, int[] B) {
        int m = A.length, n = B.length, dp[][] = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                if (A[i - 1] == B[j - 1])
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
        return dp[m][n];
    }

    // approach 1: dfs backtrack time exceeded
    public int maxUncrossedLines1(int[] A, int[] B) {
        // <value, index list>
        Map<Integer, List<Integer>> aDict = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            List<Integer> list = aDict.getOrDefault(A[i], new LinkedList<>());
            list.add(i);
            aDict.put(A[i], list);
        }
        int[] max = new int[1];
        List<int[]> connections = new ArrayList<>();
        backtrack(max, aDict, B, 0, connections);
        
        return max[0];
    }
    
    private void backtrack(int[] max, Map<Integer, List<Integer>> aDict, 
                           int[] B, int i, List<int[]> connections) {
        if (i == B.length) {
            // reach the end
            max[0] = Math.max(max[0], connections.size());
            return;
        }
        
        // not connect to any possible element in A
        backtrack(max, aDict, B, i + 1, connections);

        List<Integer> aList = aDict.get(B[i]);
        if (aList != null) {
            for (int j = 0 ; j < aList.size(); j++) {
                int aIdx = aList.get(j);
                int[] connect = new int[] {aIdx, i};
                if (canAdd(connections, connect)) {
                    connections.add(connect);
                    // remove the connected idx
                    aList.remove(j);
                    backtrack(max, aDict, B, i + 1, connections);
                    // backtrack
                    aList.add(j, aIdx);
                    connections.remove(connections.size() - 1);
                }
            }
        }
    }
    
    // tc: O(n)
    private boolean canAdd(List<int[]> connections, int[] connect) {
        for (int[] cn : connections) {
            // if there is crossing.
            // the diff of A and the diff of B have different sign.
            if ((cn[0] - connect[0]) * (cn[1] - connect[1]) < 0) {
                return false;
            }
        }
        return true;
    }
}

public class UncrossedLines {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] A = new int[] {3,1,2,1,4,1,2,2,5,3,2,1,1,4,5,2,3,2,5,5}, B = new int[] {2,4,1,2,3,4,2,4,5,5,1,1,2,1,1,1,5,4,1,4,2,1,5,4,2,3,1,5,2,1};
        int result = sol.maxUncrossedLines(A, B);
        System.out.println(result);
    }
}