package app.search.no64_minpathsum;

import java.util.PriorityQueue;

/**
 * Given a m x n grid filled with non-negative numbers, find a path from top
 * left to bottom right which minimizes the sum of all numbers along its path.
 * 
 * Note: You can only move either down or right at any point in time.
 * 
 * Example:
 * 
 * Input: [ [1,3,1], [1,5,1], [4,2,1] ] Output: 7 Explanation: Because the path
 * 1→3→1→1→1 minimizes the sum.
 */
class Solution {
    // approach 1: uniform cost search
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        
        int sum = grid[0][0];
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        queue.offer(new int[] {0, 0, sum});
        // mark it as visited
        // grid[0][0] = -1;
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1];
            sum = curr[2];
            if (row == grid.length && col == grid[0].length) {
                break;
            }
            
            // add next
            int down = row + 1, right = col + 1;
            if (down < grid.length) {
                queue.add(new int[] {down, col, sum + grid[down][col]});
            }
            if (right < grid[0].length) {
                queue.add(new int[] {row, right, sum + grid[row][right]});
            }
        }

        return sum;
    }
}

public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][] {{1,3,1},{1,5,1},{4,2,1}};
    
        Solution s = new Solution();
        System.out.println(s.minPathSum(grid));
    }
}