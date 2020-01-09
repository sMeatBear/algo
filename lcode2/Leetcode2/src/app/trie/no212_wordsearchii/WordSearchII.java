package app.trie.no212_wordsearchii;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (findWord(board, word))
                res.add(word);
        }

        return res;
    }

    /** search a single word using dfs */
    public boolean findWord(char[][] board, String word) {
        class Point {
            int row, col;

            public Point(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
        // special cases
        if (board.length == 0 || board[0].length == 0)
            return false;

        char[] wordArr = word.toCharArray();
        Deque<Point> stack;
        // record depth of dfs tree
        Deque<Integer> depths;
        boolean[][] visited = new boolean[board.length][board[0].length];

        // search
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // each time search
                stack = new ArrayDeque<>();
                depths = new ArrayDeque<>();
                Point root = new Point(i, j);
                stack.push(root);
                depths.push(0);
                // record the path (to traceback)
                Deque<Point> path = new ArrayDeque<>();

                // wordArr index
                int index = 0;
                while (!stack.isEmpty() && index < wordArr.length) {
                    Point cur = stack.pop();
                    // record the path
                    int depth = depths.pop();
                    // index need to traceback too!!
                    index = depth;
                    // traceback !!
                    // !! remove all old deep level path record
                    int totalPathRecord = depth + 1;
                    while (path.size() >= totalPathRecord) {
                        Point old = path.pop();
                        visited[old.row][old.col] = false;
                    }
                    // update path
                    path.push(cur);

                    int row = cur.row, col = cur.col;
                    // mark the point visited
                    visited[row][col] = true;
                    // can move to next letter to compare
                    if (board[row][col] == wordArr[index]) {
                        // add children (4 directions)
                        // up
                        if (row - 1 >= 0 && !visited[row - 1][col]) {
                            stack.push(new Point(row - 1, col));
                            depths.push(depth + 1);
                        }
                        // right
                        if (col + 1 < board[0].length && !visited[row][col + 1]) {
                            stack.push(new Point(row, col + 1));
                            depths.push(depth + 1);
                        }
                        // down
                        if (row + 1 < board.length && !visited[row + 1][col]) {
                            stack.push(new Point(row + 1, col));
                            depths.push(depth + 1);
                        }
                        // left
                        if (col - 1 >= 0 && !visited[row][col - 1]) {
                            stack.push(new Point(row, col - 1));
                            depths.push(depth + 1);
                        }

                        index++;
                    }
                }
                // find or not
                if (index == wordArr.length)
                    return true;
                // reset visited!!
                while (!path.isEmpty()) {
                    Point p = path.pop();
                    visited[p.row][p.col] = false;
                }
            }
        }

        return false;
    }

}

public class WordSearchII {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] board = new char[][] {
            {'b','a','a','b','a','b'},
            {'a','b','a','a','a','a'},
            {'a','b','a','a','a','b'},
            {'a','b','a','b','b','a'},
            {'a','a','b','b','a','b'},
            {'a','a','b','b','b','a'},
            {'a','a','b','a','a','b'}
        };
        String[] words = new String[] {"baababababababbbaabbbaaaa","abaabbbaaaaababbbaaaaa","ababaababaaabbabbaabbaabbaba"};
        List<String> res = s.findWords(board, words);
        System.out.println(res);
    }
}