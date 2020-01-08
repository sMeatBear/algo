package app.trie.no79_wordsearch;

class Solution {
    // DFS by recursion
    public boolean exist(char[][] board, String word) {
        // find the start letter's coordinate
        char[] wordArr = word.toCharArray();
        int row, col;

        // special case
        if (board.length == 0 || board[0].length == 0 || wordArr.length == 0) return false;
        
        // search
        // one letter only use once during each dfs
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (row = 0; row < board.length; row++) {
            for (col = 0; col < board[0].length; col++) {
                // if there exists the word then stop and return true, otherwise keep searching
                if (search(board, row, col, wordArr, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board letter board
     * @param row the coordinate of row
     * @param col the coordinate of column
     * @param wordArr word array
     * @param start current letter's index
     * @return
     */
    public boolean search(char[][] board, int row, int col, char[] wordArr, int start, boolean[][] visited) {
        // search range is not beyond the boundary of the board
        if (row >= 0 && col >= 0 && row < board.length && col < board[0].length && !visited[row][col]) {
            // end
            if (wordArr.length - 1 == start) {
                return wordArr[start] == board[row][col];
            } 
            
            // mark it as visited
            visited[row][col] = true;
            boolean found = false;
            // if current letter can match the letter in word array
            if (board[row][col] == wordArr[start]) {
                // search 4 directions
                found = search(board, row - 1, col, wordArr, start + 1, visited) ||
                        search(board, row, col + 1, wordArr, start + 1, visited) ||
                        search(board, row + 1, col, wordArr, start + 1, visited) ||
                        search(board, row, col - 1, wordArr, start + 1, visited);
            }
            // different route may have overlap so we need to traceback
            visited[row][col] = false;
            return found;
        }

        return false;
    }
}


public class WordSearch {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] board = new char[][] {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "SEE";
        boolean res = s.exist(board, word);
        System.out.println(res);
    }
}