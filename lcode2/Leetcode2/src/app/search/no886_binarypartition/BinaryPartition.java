package app.search.no886_binarypartition;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split
 * everyone into two groups of any size.
 * 
 * Each person may dislike some other people, and they should not go into the
 * same group.
 * 
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the
 * people numbered a and b into the same group.
 * 
 * Return true if and only if it is possible to split everyone into two groups
 * in this way.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]] Output: true Explanation: group1
 * [1,4], group2 [2,3] Example 2:
 * 
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]] Output: false Example 3:
 * 
 * Input: N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]] Output: false
 * 
 * 
 * Note:
 * 
 * 1 <= N <= 2000 0 <= dislikes.length <= 10000 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1] There does not exist i != j for which
 * dislikes[i] == dislikes[j].
 */
class Solution {
    // approach 3: simplified bfs
    public boolean possibleBipartition(int N, int[][] dislikes) {
        // use one list and one array
        List<List<Integer>> graph = new ArrayList<>();
        // use 0 and 1 to represent two different group
        Integer[] groupInfo = new Integer[N + 1];
        Deque<Integer> queue = new ArrayDeque<>();

        // initialize graph
        for (int i = 0; i <= N; i++) {
            graph.add(i, null);
        }
        for (int[] connect : dislikes) {
            int vertex = connect[0];
            // add neighbors
            List<Integer> adjList = graph.get(vertex);
            if (adjList == null) {
                adjList = new ArrayList<>();
            }
            adjList.add(connect[1]);
            graph.set(vertex, adjList);
        }

        // do bfs; core: if current vertex's neighbor is in the same group return false;
        for (int v = 1; v <= N; v++) {
            if (groupInfo[v] != null) {
                // visited
                continue;
            }
            // v belongs to which group
            List<Integer> vAdjList = graph.get(v);
            if (vAdjList == null) {
                // no people dislikes v
                groupInfo[v] = 0;
            } else {
                boolean group0 = false, group1 = false;
                for (int next : vAdjList) {
                    if (groupInfo[next] == null) {
                        continue;
                    }
                    if (groupInfo[next] == 1) {
                        group1 = true;
                    } else {
                        group0 = true;
                    }
                }
                if (group0 && group1) {
                    return false;
                }
                groupInfo[v] = group0 ? 1 : 0;
            }

            queue.offer(v);

            while (!queue.isEmpty()) {
                int layerSize = queue.size();
                for (int i = 0; i < layerSize; i++) {
                    int curr = queue.poll();
                    int currGroup = groupInfo[curr];
                    // generate next
                    List<Integer> adjList = graph.get(curr);
                    if (adjList != null) {
                        for (int next : adjList) {
                            // violate the rule next group == current group
                            if (groupInfo[next] == null) {
                                // group not assigned
                                groupInfo[next] = currGroup ^ 1;
                                queue.offer(next);
                            } else if (groupInfo[next] == currGroup) {
                                return false;
                            }
                            // else the next has been assigned group, which means the next node has been
                            // visited
                        }
                    }
                }
            }
        }

        return true;
    }

    // approach 1: bfs
    public boolean possibleBipartition1(int N, int[][] dislikes) {
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
                } else if (isValid(group2, dislike)) {
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
        int N = 50;
        // int[][] dislikes = new int[][]
        // {{4,7},{4,8},{5,6},{1,6},{3,7},{2,5},{5,8},{1,2},{4,9},{6,10},{8,10},{3,6},{2,10},{9,10},{3,9},{2,3},{1,9},{4,6},{5,7},{3,8},{1,8},{1,7},{2,4}};
        // int[][] dislikes = new int[][] {{1,2},{1,3},{2,4}};
        // int[][] dislikes = new int[][] {{1,2},{2,3},{3,4},{4,5},{1,5}};
        int[][] dislikes = new int[][] { { 39, 46 }, { 4, 41 }, { 3, 35 }, { 8, 44 }, { 22, 44 }, { 7, 49 }, { 28, 41 },
                { 7, 25 }, { 6, 35 }, { 2, 22 }, { 34, 35 }, { 3, 7 }, { 1, 11 }, { 11, 48 }, { 8, 24 }, { 6, 7 },
                { 38, 40 }, { 37, 48 }, { 3, 45 }, { 44, 45 }, { 4, 46 }, { 23, 35 }, { 28, 46 }, { 7, 28 }, { 35, 36 },
                { 18, 20 }, { 8, 15 }, { 17, 41 }, { 13, 35 }, { 6, 22 }, { 22, 48 }, { 22, 39 }, { 4, 35 }, { 8, 38 },
                { 23, 41 }, { 10, 41 }, { 6, 41 }, { 18, 48 }, { 16, 41 }, { 37, 44 }, { 8, 12 }, { 18, 36 },
                { 16, 18 }, { 7, 44 }, { 3, 18 }, { 10, 46 }, { 20, 37 }, { 2, 37 }, { 11, 49 }, { 30, 45 }, { 28, 37 },
                { 23, 37 }, { 22, 23 }, { 5, 37 }, { 29, 40 }, { 16, 35 }, { 22, 26 }, { 46, 49 }, { 18, 26 }, { 8, 9 },
                { 24, 46 }, { 8, 28 }, { 11, 29 }, { 22, 24 }, { 7, 15 }, { 4, 37 }, { 9, 40 }, { 8, 32 }, { 23, 40 },
                { 40, 42 }, { 33, 40 }, { 17, 45 }, { 40, 48 }, { 12, 41 }, { 43, 45 }, { 38, 41 }, { 45, 47 },
                { 12, 18 }, { 7, 31 }, { 34, 37 }, { 8, 48 }, { 4, 11 }, { 46, 48 }, { 2, 7 }, { 17, 40 }, { 12, 46 },
                { 22, 49 }, { 46, 50 }, { 37, 50 }, { 22, 36 }, { 22, 43 }, { 41, 44 }, { 13, 22 }, { 11, 16 },
                { 7, 47 }, { 14, 37 }, { 37, 43 }, { 13, 37 }, { 26, 40 }, { 19, 41 }, { 46, 47 }, { 16, 22 },
                { 19, 22 }, { 22, 33 }, { 11, 19 }, { 35, 44 }, { 7, 33 }, { 41, 49 }, { 38, 45 }, { 25, 35 },
                { 3, 37 }, { 15, 22 }, { 6, 18 }, { 11, 30 }, { 5, 41 }, { 8, 33 }, { 1, 46 }, { 31, 46 }, { 41, 42 },
                { 18, 28 }, { 15, 41 }, { 35, 49 }, { 25, 41 }, { 20, 45 }, { 26, 46 }, { 8, 43 }, { 5, 45 },
                { 28, 40 }, { 1, 18 }, { 23, 46 }, { 13, 18 }, { 35, 38 }, { 8, 49 }, { 11, 44 }, { 18, 33 }, { 4, 7 },
                { 5, 7 }, { 10, 11 }, { 37, 49 }, { 9, 22 }, { 4, 45 }, { 32, 45 }, { 32, 37 }, { 29, 35 }, { 26, 35 },
                { 7, 29 }, { 1, 37 }, { 8, 14 }, { 5, 11 }, { 18, 29 }, { 18, 49 }, { 21, 41 }, { 17, 35 }, { 7, 10 },
                { 22, 38 }, { 40, 43 }, { 5, 35 }, { 33, 35 }, { 6, 40 }, { 34, 40 }, { 22, 34 }, { 16, 40 },
                { 19, 46 }, { 18, 39 }, { 24, 35 }, { 19, 35 }, { 18, 50 }, { 8, 17 }, { 11, 12 }, { 27, 35 },
                { 8, 47 }, { 7, 9 }, { 7, 36 }, { 8, 34 }, { 7, 26 }, { 31, 41 }, { 29, 41 }, { 10, 45 }, { 9, 35 },
                { 33, 46 }, { 11, 32 }, { 34, 45 }, { 42, 46 }, { 15, 40 }, { 40, 50 }, { 30, 40 }, { 25, 40 },
                { 15, 37 } };
        System.out.println(sol.possibleBipartition(N, dislikes));
    }
}