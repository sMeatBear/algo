package app.linear.no1029_twocityscheduling;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * There are 2N people a company is planning to interview. The cost of flying
 * the i-th person to city A is costs[i][0], and the cost of flying the i-th
 * person to city B is costs[i][1].
 * 
 * Return the minimum cost to fly every person to a city such that exactly N
 * people arrive in each city.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: [[10,20],[30,200],[400,50],[30,20]] Output: 110 Explanation: The first
 * person goes to city A for a cost of 10. The second person goes to city A for
 * a cost of 30. The third person goes to city B for a cost of 50. The fourth
 * person goes to city B for a cost of 20.
 * 
 * The total minimum cost is 10 + 30 + 50 + 20 = 110 to have half the people
 * interviewing in each city.
 * 
 * 
 * Note:
 * 
 * 1 <= costs.length <= 100 It is guaranteed that costs.length is even. 1 <=
 * costs[i][0], costs[i][1] <= 1000
 */
class Solution {
    // approach 3: quick partition
    public int twoCitySchedCost(int[][] costs) {
        /*
         * Thought: 
         *   1. separate the costs to two equal size parts
         *      the first part persons go to A and the rest go to B
         *   2. actually we just need to find the median O(nlogn) which is actually faster than 
         *      quick sort
         */  
        quickPartition(costs);
        int i, bound = costs.length / 2, totalCost = 0;
        for (i = 0; i < bound; i++) {
            totalCost += costs[i][0];
        }
        for (; i < costs.length; i++) {
            totalCost += costs[i][1];
        }
        return totalCost;
    }
    
    // find the median
    private void quickPartition(int[][] costs) {
        int bound = costs.length / 2, l = 0, r = costs.length - 1;
        int pivot = partition(costs, l, r);
        while (pivot != bound && pivot != bound - 1) {
            // shrink the swap range
            if (pivot < bound) {
                l = pivot + 1;
            } else {
                r = pivot - 1;
            }
            pivot = partition(costs, l, r);
        }
    }
    
    private int partition(int[][] costs, int l, int r) {
        // just pick the l as the pivot value
        int pivot = l;
        // slow-fast-pointer
        for (int fast = l + 1; fast <= r; fast++) {
            if (compare(costs[fast], costs[l]) < 0) {
                swap(costs, ++pivot, fast);
            }
        }
        swap(costs, pivot, l);
        return pivot;
    }
    
    private int compare(int[] cost1, int[] cost2) {
        return Integer.compare(cost1[0] - cost1[1], cost2[0] - cost2[1]);
    }
    
    private void swap(int[][] costs, int i, int j) {
        int[] temp = costs[i];
        costs[i] = costs[j];
        costs[j] = temp;
    }
    
    // apporach 2: dp
    public int twoCitySchedCost2(int[][] costs) {
        int N = costs.length / 2;
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + costs[i - 1][0];
        }
        for (int j = 1; j <= N; j++) {
            dp[0][j] = dp[0][j - 1] + costs[j - 1][1];
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + costs[i + j - 1][0], dp[i][j - 1] + costs[i + j - 1][1]);
            }
        }
        return dp[N][N];
    }
    
    // approach 1: greedy
    public int twoCitySchedCost1(int[][] costs) {
        // the number of people in city A and city B
        int inA = 0, inB = 0, totalCost = 0;
        // the number of people we need to adjust (find the k smallest difference pair)
        int k = 0;
        // greedy: adjust k pairs with smallest difference (max heap)
        PriorityQueue<Integer> kSmall = new PriorityQueue<>(Collections.reverseOrder());
        // first pass
        for (int[] cost : costs) {
            if (cost[0] < cost[1]) {
                inA++;
                totalCost += cost[0];
            } else if (cost[0] > cost[1]) {
                inB++;
                totalCost += cost[1];
            } else {
                // *if there is no difference to go A and go B then we record it to adjust later
                totalCost += cost[1];
            }
        }
        
        // figure out the difference (how many people we need to adjust):
        k = Math.abs(inA - inB) / 2;
        if (k == 0) {
            return totalCost;
        }
        if (inA > inB) {
            for (int i = 0; i < costs.length; i++) {
                int diff = costs[i][1] - costs[i][0];
                if (diff > 0) {
                    kSmall.offer(diff);
                    if (kSmall.size() > k) {
                        kSmall.poll();
                    }
                }
            }
        } else {
            for (int i = 0; i < costs.length; i++) {
                int diff = costs[i][0] - costs[i][1];
                if (diff > 0) {
                    kSmall.offer(diff);
                    if (kSmall.size() > k) {
                        kSmall.poll();
                    }
                }
            }
        }
        while (!kSmall.isEmpty()) {
            totalCost += kSmall.poll();
        }
        return totalCost;
    }
}

public class TwoCitySchedule {
    public static void main(String[] args) {
       Solution sol = new Solution();
       int[][] costs = new int[][] {{518,518},{71,971},{121,862},{967,607},{138,754},{513,337},{499,873},{337,387},{647,917},{76,417}};
    //    int[][] costs = new int[][] {{10,20},{30,200},{400,50},{30,20}};
       int result = sol.twoCitySchedCost(costs);
       System.out.println(result);
    }
}