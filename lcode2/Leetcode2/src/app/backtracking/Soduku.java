package app.backtracking;

import java.util.Arrays;

class Solution {
    public void solveSudoku(char[][] board) {
        // get first position
        int[] pos = next(board, 0, -1);
        // while(pos[0] != -1){pos = next(board, pos[0], pos[1]);};
        System.out.println(solveSudoKu(board, pos[0], pos[1]));
    }

    // backtracking
    public boolean solveSudoKu(char[][] board, int r, int c) {
        // check next position
        int[] pos = next(board, r, c);
        for (int val = 1; val <= 9; val++) {
            if (isValid(board, r, c, val)) {
                board[r][c] = (char) ('0' + val);
                System.out.println("row: " + r + "," + " col: " + c + " val:" + val);

                // if this is the last element
                if (pos[0] == -1) {
                    return true;
                }

                // if not the last move to next empty
                if (solveSudoKu(board, pos[0], pos[1]))
                    return true;
                // not true, backtracking
                board[r][c] = '.';
            }
        }

        return false;
    }

    // check if it's followed the constraints
    public boolean isValid(char[][] board, int r, int c, int val) {
        char value = (char) (val + '0');
        // check row
        for (int j = 0; j < 9; j++) {
            if (board[r][j] == value)
                return false;
        }

        // check column
        for (int i = 0; i < 9; i++) {
            if (board[i][c] == value)
                return false;
        }

        // check subbox
        int boxRow = (r / 3) * 3, boxCol = (c / 3) * 3;
        int rowEnd = boxRow + 3, colEnd = boxCol + 3;

        for (int i = boxRow; i < rowEnd; i++) {
            for (int j = boxCol; j < colEnd; j++) {
                if (board[i][j] == value)
                    return false;
            }
        }

        return true;
    }

    // find next empty
    public int[] next(char[][] board, int r, int c) {
        // r: current row
        // c: current column
        // return: {rowIndex, colIndex}
        int i = r, j = c + 1;
        for (; i < 9; i++) {
            for (; j < 9; j++) {
                if (board[i][j] == '.') {
                    // System.out.println(Arrays.toString(new int[] {i , j}));
                    return new int[] { i, j };
                }
            }
            j = 0;
        }

        // no empty remains
        return new int[] { -1, -1 };
    }
}

public class Soduku {
    public static char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
    public static char[][] res = {
        {'5','3','4','6','7','8','9','1','2'},
        {'6','7','2','1','9','5','3','4','8'},
        {'1','9','8','3','4','2','5','6','7'},
        {'8','5','9','7','6','1','4','2','3'},
        {'4','2','6','8','5','3','7','9','1'},
        {'7','1','3','9','2','4','8','5','6'},
        {'9','6','1','5','3','7','2','8','4'},
        {'2','8','7','4','1','9','6','3','5'},
        {'3','4','5','2','8','6','1','7','9'}
};
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solveSudoku(board);
        for (int i = 0; i < 9; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
    }
}