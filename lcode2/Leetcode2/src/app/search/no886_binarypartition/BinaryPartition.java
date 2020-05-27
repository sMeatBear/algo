package app.search.no886_binarypartition;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.

Each person may dislike some other people, and they should not go into the same group. 

Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.

Return true if and only if it is possible to split everyone into two groups in this way.

 

Example 1:

Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
Output: true
Explanation: group1 [1,4], group2 [2,3]
Example 2:

Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
Output: false
Example 3:

Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
Output: false
 

Note:

1 <= N <= 2000
0 <= dislikes.length <= 10000
1 <= dislikes[i][j] <= N
dislikes[i][0] < dislikes[i][1]
There does not exist i != j for which dislikes[i] == dislikes[j].
 */
class Solution {
    // approach 1: bfs
    public boolean possibleBipartition(int N, int[][] dislikes) {
        // only check the restrictions
        boolean canPartition = true;
        
        Set<Integer> group1 = new HashSet<>(), group2 = new HashSet<>();
        Map<Integer, Set<Integer>> dislikeMap = new HashMap<>();
        
        for (int[] dislike : dislikes) {
            Set<Integer> dislikeMem = dislikeMap.getOrDefault(dislike[0], new HashSet<>());
            dislikeMem.add(dislike[1]);
            dislikeMap.put(dislike[0], dislikeMem);
        }
        
        // core: current node's dislikes cannot be inside current set
        Iterator<Integer> keys = dislikeMap.keySet().iterator();
        Deque<Integer> queue = new ArrayDeque<>();
        
        // in case it's not connected
        while (keys.hasNext()) {
            boolean inGroup1 = true;
            // for the first entry: tell where should we put this key
            int key = keys.next();
            if (group1.contains(key) || group2.contains(key)) {
                continue;
            } else {
                Set<Integer> dislike = dislikeMap.get(key);
                if (isValid(group1, dislike)) {
                    // key dislikes elements are not in group1
                    group1.add(key);
                } else if (isValid(group2, dislike)){
                    group2.add(key);
                    inGroup1 = false;
                } else {
                    return false;
                }
            }
            queue.offer(key);

            // bfs
            while (!queue.isEmpty()) {
                int currSize = queue.size();
                if (inGroup1) {
                    // current layer's node are all in group1
                    for (int i = 0; i < currSize; i++) {
                        int curr = queue.poll();
                        Set<Integer> next = dislikeMap.get(curr);
                        if (next != null) {
                            if (!isValid(group1, next)) {
                                return false;
                            }
                            // add to group2
                            for (int g2 : next) {
                                // as a visited structure
                                if (group2.add(g2)) {
                                    queue.offer(g2);
                                }
                            }
                        }
                    }
                    inGroup1 = false;
                } else {
                    for (int i = 0; i < currSize; i++) {
                        int curr = queue.poll();
                        Set<Integer> next = dislikeMap.get(curr);
                        if (next != null) {
                            if (!isValid(group2, next)) {
                                return false;
                            }
                            // add to group1
                            for (int g1 : next) {
                                // as a visited structure
                                if (group1.add(g1)) {
                                    queue.offer(g1);
                                }
                            }
                        }
                    }
                    inGroup1 = true;
                }
            }
        }
        
        return canPartition;
    }
    
    /** dislike element cannot be the same with any element in current group */
    private boolean isValid(Set<Integer> group, Set<Integer> next) {
        for (int n : next) {
            if (group.contains(n)) {
                return false;
            }
        }
        
        return true;
    }
}

public class BinaryPartition {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int N = 5;
        int[][] dislikes = new int[][] {{4,7},{4,8},{5,6},{1,6},{3,7},{2,5},{5,8},{1,2},{4,9},{6,10},{8,10},{3,6},{2,10},{9,10},{3,9},{2,3},{1,9},{4,6},{5,7},{3,8},{1,8},{1,7},{2,4}};
        // int[][] dislikes = new int[][] {{1,2},{1,3},{2,4}};
        System.out.println(sol.possibleBipartition(N, dislikes));
    }
}