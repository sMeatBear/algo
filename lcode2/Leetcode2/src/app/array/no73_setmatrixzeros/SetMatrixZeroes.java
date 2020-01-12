package app.array.no73_setmatrixzeros;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {
    // approach 2: two sets
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        for (int row : rows) {
            for (int col = 0; col < matrix[0].length; col++) matrix[row][col] = 0;
        }

        for (int col : cols) {
            for (int row = 0; row < matrix.length; row++) matrix[row][col] = 0;
        }
    }

    // approach 1:
    public void setZeroes1(int[][] matrix) {
        boolean[][] flip = new boolean[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    flip[i][j] = true;
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (flip[i][j]) {
                    for (int m = 0; m < matrix.length; m++) matrix[m][j] = 0;
                    for (int n = 0; n < matrix[0].length; n++) matrix[i][n] = 0;
                }
            }
        }
    }
}


public class SetMatrixZeroes {
    public static void main(String[] args) {
        int[][] matrix = new int[][] {
            {1,1,1},
            {1,0,1},
            {1,1,1}
        };

        Solution s = new Solution();
        s.setZeroes(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }
}