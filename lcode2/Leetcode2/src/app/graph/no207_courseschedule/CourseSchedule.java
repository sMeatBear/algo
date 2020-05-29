package app.graph.no207_courseschedule;

import java.util.ArrayList;
import java.util.List;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to
 * numCourses-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to
 * first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: numCourses = 2, prerequisites = [[1,0]] Output: true Explanation:
 * There are a total of 2 courses to take. To take course 1 you should have
 * finished course 0. So it is possible. Example 2:
 * 
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]] Output: false
 * Explanation: There are a total of 2 courses to take. To take course 1 you
 * should have finished course 0, and to take course 0 you should also have
 * finished course 1. So it is impossible.
 * 
 * 
 * Constraints:
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented. You may
 * assume that there are no duplicate edges in the input prerequisites. 1 <=
 * numCourses <= 10^5
 */
class Solution {
    /** find if there are some cycles in a directed graph */
    // approach 1: dfs; backtrack ! (only dfs can check the loop)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // sanity cehck
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        List<List<Integer>> graph = new ArrayList<>();
        // one path
        boolean[] visited = new boolean[numCourses];
        // checked node ! memorization
        boolean[] checked = new boolean[numCourses];
        boolean[] result = new boolean[1];
        result[0] = true;

        // build the graph
        for (int i = 0; i < numCourses; i++) {
            graph.add(i, null);
        }
        // append adjacency list
        for (int[] pre : prerequisites) {
            List<Integer> list = graph.get(pre[0]);
            if (list == null) {
                list = new ArrayList<>();
                graph.set(pre[0], list);
            }
            list.add(pre[1]);
        }

        // dfs to check if there is a cycle in each path
        for (int i = 0; i < numCourses; i++) {
            if (checked[i]) {
                continue;
            }
            // reset the visited map for this path
            visited[i] = true;
            dfs(graph, i, result, visited, checked);
            visited[i] = false;
            if (!result[0]) {
                return false;
            }
            // set all the paths expanded from i is acyclic !
            checked[i] = true;
        }

        return true;
    }

    // top-down
    private void dfs(List<List<Integer>> graph, int i, boolean[] result, boolean[] visited, boolean[] checked) {
        List<Integer> neighbors = graph.get(i);
        if (neighbors == null || !result[0]) {
            return;
        }
        for (int neighbor : neighbors) {
            if (checked[neighbor]) {
                continue;
            }
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                dfs(graph, neighbor, result, visited, checked);
                // backtack
                visited[neighbor] = false;
                // all the nodes expanded from node (neighbor) has been checked
                checked[neighbor] = true;
            } else {
                result[0] = false;
                return;
            }
        }
    }
}

public class CourseSchedule {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int numCourses = 3;
        int[][] prerequisites = new int[][] {{0,1},{0,2},{1,2}};
        boolean result = sol.canFinish(numCourses, prerequisites);
        System.out.println(result);
    }
}