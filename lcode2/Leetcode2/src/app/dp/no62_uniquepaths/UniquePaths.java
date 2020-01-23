package app.dp.no62_uniquepaths;

/**
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28
Accepted
381,311
Submissions
749,713
 */
class Solution {
    int paths = 0;
    // Combination formular
    public int uniquePaths(int m, int n) {
        int N = n + m - 2;// how much steps we need to do
        int k = m - 1; // number of steps that need to go down
        double res = 1;
        // here we calculate the total possible path number 
        // Combination(N, k) = n! / (k!(n - k)!)
        // reduce the numerator and denominator and get
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        // C = n/1 + n+1/2 + n+3/3 +...+ n+m-2/m-1
        for (int i = 1; i <= k; i++)
            res = res * (n + i - 1) / i;
        return (int)res;
    }

    // Approach 2: dp
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];
        // Special case
        if (m == 0 || n == 0) return paths;
        // dp
        // Initialize
        dp[0][0] = 0;
        // Fill first row and first column
        for (int i = 1; i < n; i++) dp[0][i] = 1;
        for (int i = 1; i < m; i++) dp[i][0] = 1;

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    // Approach 1: backtracking
    public int uniquePaths1(int m, int n) {
        backtrack(m - 1, n - 1, 0, 0);
        return paths;
    }

    /**
     * Top down
     * @param m Max row's index
     * @param n Max col's index
     * @param i Current position is in ith row
     * @param j Current position is in jth col
     */
    public void backtrack(int m, int n, int i, int j) {
        // Two direction (downwards or rightwards) that can go
        if (i == m && j == n) paths++;
        else {
            int nextI = i + 1, nextJ = j + 1;
            if (nextI <= m) backtrack(m, n, nextI, j);
            if (nextJ <= n) backtrack(m, n, i, nextJ);
        }
    }
}

public class UniquePaths {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.uniquePaths(51, 9));
    }
}