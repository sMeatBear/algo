package app.dp.no322_coinchange;

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
    public int coinChange(int[] coins, int amount) {
        return 0;
    }

    /***** Time Limit Exceeded *****/
    int count = Integer.MAX_VALUE;
    // approach 1: bf (backtracking) O(n^n)
    public int coinChange1(int[] coins, int amount) {
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
        for (int i = 0; i < coins.length && (newSum = sum + coins[i]) <= target; i++) {
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
        int amount = 13;
        int res = s.coinChange1(coins, amount);
        System.out.println(res);
    }
}