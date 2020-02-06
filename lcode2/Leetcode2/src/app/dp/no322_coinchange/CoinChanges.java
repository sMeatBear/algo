package app.dp.no322_coinchange;

import java.util.Arrays;

/**
 ** Change-Making Problem**
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.
 */
class Solution {
    // Approach 3: DP (top-down)
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;
        int[] dp = new int[amount + 1];
        return count(coins, amount, dp);
    }
    public int count(int[] coins, int curAmount, int[] dp) {
        // End 
        if (curAmount < 0) return -1;
        if (curAmount == 0) return 0;
        // Memorization
        if (dp[curAmount] != 0) return dp[curAmount];
        
        int min = Integer.MAX_VALUE;
        for (int val : coins) {
            // from deep level
            int pre = count(coins, curAmount - val, dp);
            if (pre >= 0 && pre < min) min = pre;
        }

        // Return min coins requirement under current amount 
        return (dp[curAmount] = (min == Integer.MAX_VALUE ? -1 : min + 1));
    }

    // Approach 2: Dynamic Programming (bottom-up)
    public int coinChange2(int[] coins, int amount) {
        // Sub optimization question
        // dp[i] = min{dp[i - vj] + 1 | vj is from coins} 
        int max = amount + 1;
        int[] dp = new int[max];
        // Use max val to represent the amount can't be changed (If use -1, you need to add more if statements) !!!
        Arrays.fill(dp, max);
        dp[0] = 0;

        // O(coins * amount)
        for (int i = 1; i <= amount; i++) {
            // dp[i] = min{dp[i - vj] + 1 | vj is from coins} 
            for (int val : coins) {
                int preOpt = i - val;
                // prevent overflow !!!
                if (preOpt >= 0 && dp[preOpt] != max) {
                    // Amount i can be changed
                    dp[i] = Math.min(dp[i], dp[preOpt] + 1); // Have dp[i - vj] 
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    /***** Time Limit Exceeded *****/
    int count = Integer.MAX_VALUE;
    // approach 1: bf (backtracking) O(amount^n)
    public int coinChange1(int[] coins, int amount) {
        if (amount == 0) return 0;
        backtrack(coins, 0, 0, amount);
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    /**
     * @param coins Usable value
     * @param curCnt Current used number of coin
     * @param sum Current sum
     */
    public void backtrack(int[] coins, int curCnt, int sum, int target) {
        int newSum;
        // Prevent the overflow
        for (int i = 0; i < coins.length && (newSum = sum + coins[i]) <= target && newSum > 0; i++) {
            if (newSum == target) count = Math.min(curCnt + 1, count);
            else {
                backtrack(coins, curCnt + 1, newSum, target);
            }
        }
    } 
}

public class CoinChanges {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] coins = new int[] {1, 5, 8, 10};
        int amount = 14;
        int res = s.coinChange(coins, amount);
        System.out.println(res);
    }
}