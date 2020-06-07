package app.dp.no518_coinchangeii;

/**
 * ou are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that
 * amount. You may assume that you have infinite number of each kind of coin.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: amount = 5, coins = [1, 2, 5] Output: 4 Explanation: there are four
 * ways to make up the amount: 5=5 5=2+2+1 5=2+1+1+1 5=1+1+1+1+1 Example 2:
 * 
 * Input: amount = 3, coins = [2] Output: 0 Explanation: the amount of 3 cannot
 * be made up just with coins of 2. Example 3:
 * 
 * Input: amount = 10, coins = [10] Output: 1
 * 
 * 
 * Note:
 * 
 * You can assume that
 * 
 * 0 <= amount <= 5000 1 <= coin <= 5000 the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 */

class Solution {
    // approach 2: dp
    // approach 2: dp
    public int change(int amount, int[] coins) {
        // sanity check
        // ...
        // 2 dim dp; dp[i][j] means the combination of use i coins to fit amount j
        //   base case:
        //     1. dp[0][0] = 1;
        //     2. dp[i][0] = 1;
        //     3. dp[0][j] = 0;
        //   induction:
        //     1. take the coin or not
        //        dp[i][j] = dp[i - 1][j] + dp[<em>i</em>][j - coins[i-1]]
        
        // find the min and fill the base case
        int[][] dp = new int[coins.length + 1][amount + 1];
        // fill the table
        for (int i = 1; i <= coins.length; i++) {
            // base case
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j] + (j >= coins[i - 1] ? dp[i][j - coins[i - 1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }
    
    // approach 1: dfs (Time limit exceeded) tc: (m^n), sc: O(n) n -- length of coins, m -- amount
    public int change1(int amount, int[] coins) {
        int[] result = new int[1];
        dfs(coins, 0, amount, result);
        return result[0];
    }
    
    private void dfs(int[] coins, int index, int remaining, int[] result) {
        // base case
        if (remaining == 0) {
            result[0]++;
            return;
        }
        if (index >= coins.length || remaining < coins[index]) {
            return;
        }
        // recursion
        // the max number of coins[index] that we can use
        int total = remaining / coins[index];
        for (int numUse = 0; numUse <= total; numUse++) {
            // how many coins[index] do we use ?
            int newRemain = remaining - numUse * coins[index];
            dfs(coins, index + 1, newRemain, result);
        }
    }
}

public class CoinChangeII {
    public static void main(String[] args) {
        int amount = 5;
        int[] coins = new int[] {1, 2, 5};
        Solution sol = new Solution();
        System.out.println(sol.change(amount, coins));
    }
}