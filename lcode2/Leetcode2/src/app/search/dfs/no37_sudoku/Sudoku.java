package app.search.dfs.no37_sudoku;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * 
 * A sudoku solution must satisfy all of the following rules:
 * 
 * Each of the digits 1-9 must occur exactly once in each row. Each of the
 * digits 1-9 must occur exactly once in each column. Each of the the digits 1-9
 * must occur exactly once in each of the 9 3x3 sub-boxes of the grid. Empty
 * cells are indicated by the character '.'.
 * 
 * 
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    // build the hash table (represent if the number exists)
    boolean[][] rows = new boolean[9][10];
    boolean[][] cols = new boolean[9][10];
    boolean[][] square = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        // fill the hash table
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rows[i][num] = true;
                    cols[j][num] = true;
                    int squareIdx = sqIdx(i, j);
                    square[squareIdx][num] = true;
                }
            }
        }

        fill(board, 0, 0);
    }

    private boolean fill(char[][] board, int i, int j) {
        if (i == board.length) {
            return true;
        }
        if (board[i][j] != '.') {
            if (j < board[0].length - 1) {
                return fill(board, i, j + 1);
            }
            return fill(board, i + 1, 0);
        }

        // fill
        for (int num = 1; num <= 9; num++) {
            if (isValid(i, j, num)) {
                board[i][j] = (char) (num + '0');
                // mark it as visited
                rows[i][num] = true;
                cols[j][num] = true;
                int squareIdx = sqIdx(i, j);
                square[squareIdx][num] = true;

                boolean res = false;
                if (j < board[0].length - 1) {
                    res = fill(board, i, j + 1);
                } else {
                    res = fill(board, i + 1, 0);
                }
                if (res) {
                    return true;
                }
                // backtrack
                rows[i][num] = false;
                cols[j][num] = false;
                square[squareIdx][num] = false;
            }
        }
        // **backtrack
        board[i][j] = '.';
        return false;
    }

    private boolean isValid(int i, int j, int num) {
        int squareIdx = sqIdx(i, j);
        return !(rows[i][num] || cols[j][num] || square[squareIdx][num]);
    }

    private int sqIdx(int i, int j) {
        return i / 3 * 3 + j / 3;
    }
}

public class Sudoku {
    public static void main(String[] args) {
        char[][] board = new char[][] { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
        Solution sol = new Solution();
        sol.solveSudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
