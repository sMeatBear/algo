class NQueenSolution {
    private int count;
    // traverse all the possible
    public int totalNQueens(int n) {
        // initiate board to be -1 for each row marking unplaced board
        int[] board = new int[n];
        for (int i = 0; i < n; i++) {
            board[i] = -1;
        }
        
        totalNQueens(0, board);
        return count;
    }
    
    public void totalNQueens(int r, int[] board) {
        for (int j = 0; j < board.length; j++) {
            // check if we can place a queen here
            if (isPlacable(r, j, board)) {
                // reach the last row
                if (r == board.length - 1) count++;
                else {
                    // move to next row
                    board[r] = j;
                    totalNQueens(r + 1, board);
                }
                
                // remove the row to check other possible
                board[r] = - 1;
            }
        }        
    }
    
    public boolean isPlacable(int r, int c, int[] board) {
        for (int i = 0; i < r; i++) {
            // same col or diagonal
            if (board[i] == c || Math.abs(board[i] - c) == Math.abs(i - r))
                return false;
        }
        return true;
    }
}

public class NQueens {
    public static void main(String[] args) {
        NQueenSolution s = new NQueenSolution();
        int res = s.totalNQueens(4);
        System.out.println(res);
    }
}