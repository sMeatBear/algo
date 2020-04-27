package app.array.diagonaltraversal;

import app.Timer;

public class DiagonalTraversal {
    public static void diagonal(int[][] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // bottom left traverse
        for (int i = nums.length - 1; i > 0; i--) {
            // i means current start row number
            for (int row = i, col = 0; row < nums.length && col < nums[0].length; row++, col++) {
                // col offset is current row no. - start row no.
                System.out.print(nums[row][col] + " ");
            }
            System.out.println();
        }

        // top right traverse
        for (int j = 0; j < nums[0].length; j++) {
            // j means the start column no.
            for (int row = 0, col = j; row < nums.length && col < nums[0].length; row++, col++) {
                System.out.print(nums[row][col] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] nums = new int[][] {
            {1,2},
            {7,5},
            {4,1},
        };

        Timer.execute(DiagonalTraversal.class, "diagonal", new Object[] {nums}, int[][].class);
    }
}