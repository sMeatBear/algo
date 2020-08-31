package app.graph.no952_largestcomponent;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // graph + dfs
    @SuppressWarnings("unchecked")
    public int largestComponentSize(int[] A) {
        // build the graph
        List<Integer>[] graph = new ArrayList[A.length];
        // initialize
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = i + 1; j < A.length; j++) {
                // check the gcd is 1 or not
                if (gcd(A[i], A[j]) != 1) {
                    graph[i].add(j);
                    graph[j].add(i);
                }
            }
        }
        
        int[] count = new int[1], max = new int[1];
        boolean[] visited = new boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            // incase the graph is not connected
            if (!visited[i]) {
                dfs(graph, i, visited, count, max);
            }
            count[0] = 0;
        }
        
        return max[0];
    }
    
    private void dfs(List<Integer>[] graph, int i, boolean[] visited, int[] count, int[] max) {
        if (visited[i]) {return;}
        
        visited[i] = true;
        count[0]++;
        max[0] = Math.max(max[0], count[0]);
        List<Integer> next = graph[i];
        for (int n : next) {
            dfs(graph, n, visited, count, max);
        }
    }
    
    private int gcd(int a, int b) {
        int mod;
        while ((mod = a % b) != 0) {
            a = b;
            b = mod;
        }
        return b;
    }
}

public class LargestComponentSize {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] A = new int[] {20,50,9,63,81};
        int res = sol.largestComponentSize(A);
        System.out.println(res);
    }
}