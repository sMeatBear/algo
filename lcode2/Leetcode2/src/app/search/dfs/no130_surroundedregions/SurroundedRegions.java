package app.search.dfs.no130_surroundedregions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions
 * surrounded by 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded
 * region.
 * 
 * Example:
 * 
 * X X X X X O O X X X O X X O X X After running your function, the board should
 * be:
 * 
 * X X X X X X X X X X X X X O X X Explanation:
 * 
 * Surrounded regions shouldn’t be on the border, which means that any 'O' on
 * the border of the board are not flipped to 'X'. Any 'O' that is not on the
 * border and it is not connected to an 'O' on the border will be flipped to
 * 'X'. Two cells are connected if they are adjacent cells connected
 * horizontally or vertically.
 */

class Solution {
    /**
     * approach 2: from boundary to center
     *   core: start from 4 bound and mark all connected node
     */
    public void solve(char[][] board) {
        // sanity check
        if (board == null || board.length == 0) {
            return;
        }

        // traverse all nodes in four boundary
        int lastRowIdx = board.length - 1, lastColIdx = board[0].length - 1;
        for (int col = 0; col < board[0].length; col++) {
            // top and bottom
            if (board[0][col] == 'O') {
                dfsForApp2(board, 0, col);
            }
            if (board[lastRowIdx][col] == 'O') {
                dfsForApp2(board, lastRowIdx, col);
            }
        }
        for (int row = 0; row < board.length; row++) {
            // left and right
            if (board[row][0] == 'O') {
                dfsForApp2(board, row, 0);
            }
            if (board[row][lastColIdx] == 'O') {
                dfsForApp2(board, row, lastColIdx);
            }
        }

        // post processing
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] == 'V' ? 'O' : 'X';
            }
        }
    }

    private void dfsForApp2(char[][] board, int row, int col) {
        // base case
        if (outOfBound(board, row, col) || board[row][col] == 'V' || board[row][col] == 'X') {
            return;
        }
        // mark it as visited
        board[row][col] = 'V';
        // dfs
        dfsForApp2(board, row - 1, col);
        dfsForApp2(board, row, col + 1);
        dfsForApp2(board, row + 1, col);
        dfsForApp2(board, row, col - 1);
    }

    // approach 1: plain dfs
    public void solve1(char[][] board) {
        // sanity check
        if (board == null || board.length == 0) {
            return;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        // search range is all the node except the boundary
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // not 'X' and visited
                if (board[i][j] != 'X' && !visited[i][j]) {
                    // traverse the connected point and mark them all
                    handleSurrounded(board, i, j, visited);
                }
            }
        }
    }

    private void handleSurrounded(char[][] board, int i, int j, boolean[][] visited) {
        // coordinate pair
        List<int[]> path = new ArrayList<>();
        if (dfs(board, i, j, path, visited)) {
            for (int[] node : path) {
                int row = node[0], col = node[1];
                board[row][col] = 'X';
            }
        }
    }

    private boolean dfs(char[][] board, int row, int col, List<int[]> path, boolean[][] visited) {
        /* base case */
        // reach the bound
        if (outOfBound(board, row, col)) {
            return false;
        }
        if (board[row][col] == 'X' || visited[row][col]) {
            return true;
        }

        /* current layer */
        visited[row][col] = true;
        path.add(new int[] { row, col });
        // dfs
        boolean result = dfs(board, row - 1, col, path, visited);
        result = dfs(board, row, col + 1, path, visited) && result;
        result = dfs(board, row + 1, col, path, visited) && result;
        result = dfs(board, row, col - 1, path, visited) && result;
        return result;
    }

    private boolean outOfBound(char[][] board, int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[0].length;
    }
}

public class SurroundedRegions {
    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = new char[][] {{'O','O','O','O','X','X'},{'O','O','O','O','O','O'},{'O','X','O','X','O','O'},{'O','X','O','O','X','O'},{'O','X','O','X','O','O'},{'O','X','O','O','O','O'}};
        sol.solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}