package app.dp.no174_dungeongame;

import java.util.PriorityQueue;

/**
 * The demons had captured the princess (P) and imprisoned her in the
 * bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid
 * out in a 2D grid. Our valiant knight (K) was initially positioned in the
 * top-left room and must fight his way through the dungeon to rescue the
 * princess.
 * 
 * The knight has an initial health point represented by a positive integer. If
 * at any point his health point drops to 0 or below, he dies immediately.
 * 
 * Some of the rooms are guarded by demons, so the knight loses health (negative
 * integers) upon entering these rooms; other rooms are either empty (0's) or
 * contain magic orbs that increase the knight's health (positive integers).
 * 
 * In order to reach the princess as quickly as possible, the knight decides to
 * move only rightward or downward in each step.
 * 
 * 
 * 
 * Write a function to determine the knight's minimum initial health so that he
 * is able to rescue the princess.
 * 
 * For example, given the dungeon below, the initial health of the knight must
 * be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN -> DOWN.
 * 
 * -2 (K) -3 3 -5 -10 1 10 30 -5 (P)
 * 
 * 
 * Note:
 * 
 * The knight's health has no upper bound. Any room can contain threats or
 * power-ups, even the first room the knight enters and the bottom-right room
 * where the princess is imprisoned.
 */
class Solution {
    // approach 4': space opt -> O(n)
    public int calculateMinimumHP(int[][] dungeon) {
        // sanity check
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        
        int m = dungeon.length, n = dungeon[0].length;
        int[] hp = new int[n];
        // base case
        hp[n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
        for (int j = n - 2; j >= 0; j--) {
            hp[j] = Math.max(hp[j + 1] - dungeon[m - 1][j], 1);
        }
        
        // induction
        for (int i = m - 2; i >= 0; i--) {
            hp[n - 1] = Math.max(hp[n - 1] - dungeon[i][n - 1], 1);
            for (int j = n - 2; j >= 0; j--) {
                hp[j] = Math.max(Math.min(hp[j], hp[j + 1]) - dungeon[i][j], 1);
            }
        }
        return hp[0];
    }
    // approach 4: dp bottom up
    public int calculateMinimumHP4(int[][] dungeon) {
        /*
         * We must reach the princess and fight then survive (with at least 1 hp)
         * 1. the meaning of the cell
         *    hp[i][j] means the min hp to survive in this cell
         * 2. base case
         *    hp[m][n] = 1 - dungeon[m][n] (m, n are the princess cell)
         *    hp[i][n] = Math.max(hp[i + 1][n] - dungeon[i][n], 1);
         *    hp[m][i] = ... (same above)
         * 3. induction:
         *    hp[i][j] we pick small min hp to go (rightward or downward):
         *             Math.max(Math.min(hp[i + 1][j], hp[i][j + 1]) - dungeon[i][j], 1);
         */
        // sanity check
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        
        int m = dungeon.length, n = dungeon[0].length;
        int[][] hp = new int[m][n];
        // base case
        hp[m - 1][n - 1] = Math.max(1 - dungeon[m - 1][n - 1], 1);
        for (int i = m - 2; i >= 0; i--) {
            hp[i][n - 1] = Math.max(hp[i + 1][n - 1] - dungeon[i][n - 1], 1);
        }
        for (int j = n - 2; j >= 0; j--) {
            hp[m - 1][j] = Math.max(hp[m - 1][j + 1] - dungeon[m - 1][j], 1);
        }
        
        // induction bottom up
        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                hp[i][j] = Math.max(Math.min(hp[i + 1][j], hp[i][j + 1]) - dungeon[i][j], 1);
            }
        }
        return hp[0][0];
    }
    
    // approach 3: dp2 failed too
    public int calculateMinimumHP3(int[][] dungeon) {
        // sanity check
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        
        // assume the beginning hp is 0
        // [[current health remain, global min health remain]]
        int[][] dp = new int[dungeon[0].length][];
        dp[0] = new int[] {dungeon[0][0], Math.min(0, dungeon[0][0])};
        // fill the table top down
        // base case: first line (optimize space)
        for (int j = 1; j < dungeon[0].length; j++) {
            int currCost = dp[j - 1][0] + dungeon[0][j];
            dp[j] = new int[] {currCost, Math.min(dp[j - 1][1], currCost)};
        }
        // induction
        for (int i = 1; i < dungeon.length; i++) {
            int[][] newDp = new int[dungeon[0].length][];
            int currCost = dp[0][0] + dungeon[i][0];
            newDp[0] = new int[] {currCost, Math.min(currCost, dp[0][1])};
            for (int j = 1; j < dungeon[0].length; j++) {
                // ** compute current min and global min separately
                currCost = Math.max(dp[j][0], newDp[j - 1][0]) + dungeon[i][j];
                newDp[j] = new int[] {currCost, Math.min(currCost, Math.max(dp[j][1], newDp[j - 1][1]))};
            }
            dp = newDp;
        }
        return -dp[dungeon[0].length - 1][1] + 1;
    }
    
    // approach 2: UCS (time limit exceeded)
    public int calculateMinimumHP2(int[][] dungeon) {
        // poll the cell with min cost (max val) first 
        // sanity check
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        
        // initialization
        boolean[][] visited = new boolean[dungeon.length][dungeon[0].length];
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[2], a[2]));
        int minHealth = 0;
        // {row, col, val}
        maxHeap.offer(new int[] {0, 0, dungeon[0][0]});
        
        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            int row = curr[0], col = curr[1], health = curr[2];
            // update min
            minHealth = minHealth > health ? health : minHealth;
            // check if reach the end
            if (row == dungeon.length - 1 && col == dungeon[0].length - 1) {
                return minHealth < 0 ? -minHealth + 1 : 1;
            }
            // visited[row][col] = true;
            
            // expand
            int newRow = row + 1, newCol = col + 1;
            // if (newRow < dungeon.length && !visited[newRow][col]) {
            if (newRow < dungeon.length) {
                maxHeap.offer(new int[] {newRow, col, health + dungeon[newRow][col]});
            }
            // if (newCol < dungeon[0].length && !visited[row][newCol]) {
            if (newCol < dungeon[0].length) {
                maxHeap.offer(new int[] {row, newCol, health + dungeon[row][newCol]});
            }
        }
        return minHealth < 0 ? -minHealth + 1 : 1;
    }
    
    // approach 1: dp failed
    public int calculateMinimumHP1(int[][] dungeon) {
        /*
         * dp[i][j] represents the least cost to reach this cell (i, j)
         * base case:
         *   dp[0][0] = dungeon[0][0]
         *   dp[i][0] = dp[i - 1][0] + dungeon[i][0]
         *   dp[0][j] = dp[0][j - 1] + dungeon[0][j];
         * induction:
         *   dp[i][j] = max{dp[i-1][j], dp[i][j-1]} + dungeon[i][j]
         */
        // sanity check
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0) {
            return 0;
        }
        
        int[] dp = new int[dungeon[0].length];
        dp[0] = dungeon[0][0];
        // fill the table top down
        // base case: first line (optimize space)
        for (int j = 1; j < dungeon[0].length; j++) {
            dp[j] = dp[j - 1] + dungeon[0][j];
        }
        // induction
        for (int i = 1; i < dungeon.length; i++) {
            int[] newDp = new int[dungeon[0].length];
            newDp[0] = dp[0] + dungeon[i][0];
            for (int j = 1; j < dungeon[0].length; j++) {
                newDp[j] = Math.max(dp[j], newDp[j - 1]) + dungeon[i][j];
            }
            dp = newDp;
        }
        return dp[dungeon[0].length - 1] + 1;
    }
}

public class DungeonGame {
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] dungeon = new int[][] {{-2,-3,3},{-5,-10,1},{10,30,-5}};
        int res = sol.calculateMinimumHP(dungeon);
        System.out.println(res);
    }
}