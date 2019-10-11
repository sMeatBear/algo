package app.linear;

class ZigZagSolution {
    public int[] findDiagonalOrder(int[][] matrix) {
        
        int rows = matrix.length;
        if (rows == 0) return new int[0];
        
        int cols = matrix[0].length;
        int[] res = new int[rows * cols];
        
        // direction
        int d = 1;
        // column
        int col = 0;
        int row = 0;
        int i = 0;
        // build nums[] array
        int len = matrix.length + matrix[0].length - 1;
        int largerD, smallerD;
        if (matrix.length > matrix[0].length) {
            largerD = matrix.length - 1;
            smallerD = matrix[0].length - 1;
        } else {
            largerD = matrix[0].length - 1;
            smallerD = matrix.length - 1;
        }
        int[] nums = new int[len];
        for (int index = 0; index < len; index++) {
            if (index <= largerD) {
                nums[index] = Math.min(index, smallerD);
            } else {
                nums[index] = --smallerD;
            }
        }

        int q = 0;
        while (i < res.length) {
            // this traverse numbers ///
            int num = nums[q];
            q++;
            
            int sRow = row, sCol = col; 
            if (d > 0) {
                sRow = row + d * num;
                sCol = col - d * num;
            }
            
            for (int j = 0; j <= num; j++) {
                // System.out.println(sRow + ", " + sCol);
                res[i] = matrix[sRow][sCol];
                sRow = sRow - d;
                sCol = sCol + d;
                i++;
            }
            
            // change direction
            d = -d;
            if (col < matrix[0].length) {col++;}
            if (col == matrix[0].length) {
                col = matrix[0].length - 1;
                row++;
            }
        }
        
        return res;
    }
}

public class Zigzag {
    public static void main(String[] args) {
        ZigZagSolution s = new ZigZagSolution();
        int[][] matrix = {{5,2}, {7,1}, {7,1}};
        s.findDiagonalOrder(matrix);
    }
}