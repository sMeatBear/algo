package app.dp.knapsack;

import java.util.Arrays;

public class Knapsack {
    /** 01 knapsack */
    // tc, sc : O(nc)
    public static int[][] knapsack01(int[] weights, int capacity) {
        // sanity check
        if (weights == null || weights.length == 0 || capacity <= 0) {
            return new int[][] {{0}, new int[weights.length]};
        }
        /**
         * Thought:
         * 1. we construct a table: row means ith item and column means the weight we can reach
         * e.g. dp[i][j] means we can use ith item (we can take or not take) to reach the weight j
         * base case:
         *   dp[0][0] = true
         *   dp[i][0] = true
         *   dp[0][j(!=0)] = false
         * induction:
         *   dp[i][j] = dp[i-1][j] || dp[i-1][j-weights[i-1]]
         */
        int max = 0;
        int[] result = new int[weights.length];
        boolean[][] dp = new boolean[weights.length + 1][capacity + 1];
        dp[0][0] = true;
        // base case
        for (int i = 0; i < weights.length; i++) {
            dp[i + 1][0] = true;
        }
        // induction
        for (int i = 1; i <weights.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                dp[i][j] = j - weights[i - 1] < 0 ? dp[i - 1][j] : 
                           dp[i - 1][j] || dp[i - 1][j - weights[i - 1]];
                if (dp[i][j]) {
                    // update max
                    max = max > j ? max : j;
                }
            }
        }

        // find the path (only one possible path)
        for (int i = weights.length - 1, j = max; i >= 0; i--) {
            if (dp[i][j]) {
                // if we take ith item then dp[i+1][j-weights[i]] should be true !
                result[i] = 0;
            } else {
                result[i] = 1;
                j = j - weights[i];
            }
        }
        return new int[][] {{max}, result};
    }

    public static void main(String[] args) {
        int[] weights = new int[] {14,8,19,6,16,3,11};
        int capacity = 18;
        int[][] result = knapsack01(weights, capacity);
        System.out.println(Arrays.deepToString(result));
    }
}