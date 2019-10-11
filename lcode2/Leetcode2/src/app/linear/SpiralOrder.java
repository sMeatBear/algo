package app.linear;

import java.util.ArrayList;
import java.util.List;

class SpiralOrderSolution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        // a loop right, down, left, up

        // special case
        if (matrix.length == 0 || matrix[0].length == 0) return res;

        // initialize bound
        int rBound = matrix[0].length - 1;
        int uBound = 1;
        int dBound = matrix.length - 1;
        int lBound = 0;
        int total = matrix.length * matrix[0].length;

        int i = 0;
        int r = 0, c = 0;
        while (true) {
            // four direction
            for (int d = 0; d < 4; d++) {
                switch (d) {
                    case 0:
                        // right
                        while (c <= rBound) {
                            res.add(matrix[r][c]);
                            i++;
                            c++;
                        }
                        // current right
                        c--;
                        r++;
                        // update bound
                        rBound--;
                        break;
                    case 1:
                        // down
                        while (r <= dBound) {
                            res.add(matrix[r][c]);
                            i++;
                            r++;
                        }
                        // current down
                        r--;
                        c--;
                        // update bound
                        dBound--;
                        break;
                    case 2:
                        // left
                        while (c >= lBound) {
                            res.add(matrix[r][c]);
                            i++;
                            c--;
                        }
                        // current left
                        c++;
                        r--;
                        // update bound
                        lBound++;
                        break;
                    case 3:
                        // up
                        while (r >= uBound) {
                            res.add(matrix[r][c]);
                            i++;
                            r--;
                        }
                        // current down
                        r++;
                        c++;
                        // update bound
                        uBound++;
                        break;
                }
            }
            if (i >= total) break;
        }
        
        
        return res.subList(0, total);
    }
}

public class SpiralOrder {
    public static void main(String[] args) {
        SpiralOrderSolution s = new SpiralOrderSolution();
        int[][] matrix = {{1,2}, {2,3}, {3,4},{4,5}};
        List<Integer> res = s.spiralOrder(matrix);
        System.out.println(res);
    }
}