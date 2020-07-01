package app.search.no212_wordsearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * 
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 * 
 * 
 * 
 * Example:
 * 
 * Input: board = [ ['o','a','a','n'], ['e','t','a','e'], ['i','h','k','r'],
 * ['i','f','l','v'] ] words = ["oath","pea","eat","rain"]
 * 
 * Output: ["eat","oath"]
 * 
 * 
 * Note:
 * 
 * All inputs are consist of lowercase letters a-z. The values of words are
 * distinct.
 */

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        // sanity check
        Set<String> result = new HashSet<>();
        if (board == null) {
            return new ArrayList<>(result);
        }
        Trie t = new Trie(words);
        TrieNode head = t.head;
        
        // dfs
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                dfs(board, i, j, head, result, visited);
            }
        }
        
        return new ArrayList<>(result);
    }
    
    private void dfs(char[][] board, int row, int col, TrieNode head, Set<String> result, boolean[][] visited) {
        TrieNode curr = head.getNext(board[row][col]);
        if (curr == null) {
            return;
        }
        if (curr.isEnd) {
            result.add(Trie.toString(curr));
        }
        visited[row][col] = true;
        int up = row - 1, right = col + 1, down = row + 1, left = col - 1;
        // up
        if (up >= 0 && !visited[up][col]) {
            dfs(board, up, col, curr, result, visited);
        }
        // right
        if (right < board[0].length && !visited[row][right]) {
            dfs(board, row, right, curr, result, visited);
        }
        // down
        if (down < board.length && !visited[down][col]) {
            dfs(board, down, col, curr, result, visited);
        }
        // left
        if (left >= 0 && !visited[row][left]) {
            dfs(board, row, left, curr, result, visited);
        }
        // !backtrack
        visited[row][col] = false;
    }
}
class TrieNode {
    boolean isEnd;
    char ch;
    TrieNode prev;
    TrieNode[] link = new TrieNode[26];
    public TrieNode(char ch) {
        this.ch = ch;
    }

    public TrieNode getNext(char ch) {
        return link[ch - 'a'];    
    }
}

class Trie {
    TrieNode head;
    public Trie(String[] words) {
        head = new TrieNode(' ');
        for (String word : words) {
            add(word);
        }
    }
    
    public boolean add(String word) {
        if (word == null) {
            return false;
        }
        
        int i;
        TrieNode curr = head;
        for (i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = ch - 'a';
            if (curr.link[idx] == null) {
                curr.link[idx] = new TrieNode(ch);
                curr.link[idx].prev = curr;
            }
            curr = curr.link[idx];
        }
        return curr.isEnd ? false : (curr.isEnd = true);
    }
    
    public static String toString(TrieNode node) {
        Deque<Character> stack = new ArrayDeque<>();
        while (node != null) {
            stack.push(node.ch);
            node = node.prev;
        }
        // pop the head
        stack.pop();
        StringBuilder sbd = new StringBuilder();
        while (!stack.isEmpty()) {
            sbd.append(stack.pop());
        }
        return sbd.toString();
    }
}

public class WordSearch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = new char[][] {{'a', 'b'}, {'a', 'a'}};
        String[] words = new String[] {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        System.out.println(sol.findWords(board, words));
    }
}