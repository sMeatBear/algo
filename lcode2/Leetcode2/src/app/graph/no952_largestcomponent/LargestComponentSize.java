package app.graph.no952_largestcomponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a non-empty array of unique positive integers A, consider the following graph:

There are A.length nodes, labelled A[0] to A[A.length - 1];
There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
Return the size of the largest connected component in the graph.

 

Example 1:

Input: [4,6,15,35]
Output: 4


来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/largest-component-size-by-common-factor
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    static class UnionFinder {
        int[] parent;
        UnionFinder(int len) {
            parent = new int[len];
            for (int i = 0; i < parent.length; i++) {
                parent[i] = i;
            }
        }

        int find(int i) {
            // lazy change unify the index of same set
            if (i != parent[i]) {parent[i] = find(parent[i]);}
            return parent[i];
        }

        void union(int i, int j) {
            // parent[find(i)] -> trace back to the position that i == parent[i] and then change it.
            parent[find(i)] = find(j);
        }
    }

    public int largestComponentSize(int[] A) {
        List<Integer>[] factors = new ArrayList[A.length];
        // find all PRIME factors for each element in A except 1
        for (int i = 0; i < A.length; i++) {
            int factor = 2;
            factors[i] = new ArrayList<>();
            int x = A[i];
            while (factor * factor <= x) {
                if (x % factor == 0) {
                    while (x % factor == 0) {
                        x /= factor;
                    }
                    factors[i].add(factor);
                }
                factor++;
            }
            // last prime factor
            if (x > 1 || factors[i].isEmpty()){
                factors[i].add(x);
            }
        }

        // create factor set
        Set<Integer> factorSet = new HashSet<>();
        for (List<Integer> fs : factors) {
            for (int f : fs) {
                factorSet.add(f);
            }
        }

        // build the factor list and map
        int[] factorArr = new int[factorSet.size()];
        Map<Integer, Integer> factorMap = new HashMap<>();
        int fIdx = 0;
        for (int f : factorSet) {
            factorMap.put(f, fIdx);
            factorArr[fIdx++] = f;
        }

        // build the union set
        UnionFinder uf = new UnionFinder(factorArr.length);
        for (List<Integer> fs : factors) {
            int fsIdx = factorMap.get(fs.get(0));
            for (int f : fs) {
                uf.union(fsIdx, factorMap.get(f));
            }
        }

        // find the max connected factor union set
        int res = 0;
        int[] count = new int[factorArr.length];
        for (List<Integer> fs : factors) {
            // each node's factors' group
            res = Math.max(++count[uf.find(factorMap.get(fs.get(0)))], res);
        }
        return res;
    }
}

class Solution2 {
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
        int[] A = new int[] {4,6,15,35};
        int res = sol.largestComponentSize(A);
        System.out.println(res);
    }
}