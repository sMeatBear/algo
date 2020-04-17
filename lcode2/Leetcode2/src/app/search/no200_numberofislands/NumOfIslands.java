package app.search.no200_numberofislands;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 *Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3
 */
class Solution {
    // approach 2: dfs
    public int numIslands(char[][] grid) {
        int numOfIslands = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfsRecursive(grid, i, j);
                    numOfIslands++;
                }
            }
        }
        
        return numOfIslands;
    }

    public void dfs(char[][] grid, int row, int col) {
        Deque<int[]> stack = new ArrayDeque<>();

        stack.push(new int[] {row, col});
        grid[row][col] = '0';
        while (!stack.isEmpty()) {
            int[] curr = stack.pop();
            List<int[]> nexts = getNext(grid, curr[0], curr[1]);

            for (int[] next : nexts) {
                stack.push(next);
                grid[next[0]][next[1]] = '0';
            }
        }

    }

    // recursive dfs
    public void dfsRecursive(char[][] grid, int row, int col) {
        if (row < grid.length && 
            row >= 0 && 
            col < grid[0].length && 
            col >= 0 &&
            grid[row][col] == '1') {
            grid[row][col] = '0';

            // next
            dfsRecursive(grid, row - 1, col);
            dfsRecursive(grid, row, col + 1);
            dfsRecursive(grid, row + 1, col);
            dfsRecursive(grid, row, col - 1);
        }
    }
    
    // approach 1: bfs
    public int numIslands1(char[][] grid) {
        int numOfIslands = 0;
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    numOfIslands++;
                }
            }
        }
        
        return numOfIslands;
    }
    
    // bfs
    public void bfs(char[][] grid, int row, int col) {
        Deque<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {row, col});
        // mark it as visited (sink the visited point !)
        grid[row][col] = '0';
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            List<int[]> nexts = getNext(grid, curr[0], curr[1]);
            
            for (int[] next : nexts) {
                grid[next[0]][next[1]] = '0';
                queue.offer(next);
            }
        }
    }
    
    public List<int[]> getNext(char[][] grid, int row, int col) {
        List<int[]> nexts = new ArrayList<>();
        int up = row - 1, down = row + 1, left = col - 1, right = col + 1;
        // up
        if (up >= 0 && grid[up][col] != '0') {
            nexts.add(new int[] {up, col});
        }
        // right
        if (right < grid[0].length && grid[row][right] != '0') {
            nexts.add(new int[] {row, right});
        }
        // down
        if (down < grid.length && grid[down][col] != '0') {
            nexts.add(new int[] {down, col});
        }
        // left
        if (left >= 0 && grid[row][left] != '0') {
            nexts.add(new int[] {row, left});
        }
        
        return nexts;
    }
}

public class NumOfIslands {

}