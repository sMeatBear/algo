package app.search.no212_wordsearch.second_practice;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        Trie t = new Trie(words);
        TrieNode root = t.head;
        boolean[][] visited = new boolean[board.length][board[0].length];
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // if the head is not null
                int idx = Trie.char2idx(board[i][j]);
                if (root.next[idx] != null) {
                    dfs(board, root, i, j, visited, result);
                }
            }
        }
        return new ArrayList<>(result);
    }
    
    private void dfs(char[][] board, TrieNode root, int row, int col, boolean[][] visited, Set<String> result) {
        // base case
        if (row < 0 || row >= board.length ||
            col < 0 || col >= board[0].length || visited[row][col]) {
            return;
        }
        
        char ch = board[row][col];
        int idx = Trie.char2idx(ch);
        TrieNode next = root.next[idx];
        // current layer comparsion
        if (next != null) {
            visited[row][col] = true;
            // end condition
            if (next.isEnd) {result.add(next.word);}
            // dfs part
            dfs(board, next, row - 1, col, visited, result);
            dfs(board, next, row + 1, col, visited, result);
            dfs(board, next, row, col - 1, visited, result);
            dfs(board, next, row, col + 1, visited, result);
            // backtrack
            visited[row][col] = false;
        }
    }
    
}

class TrieNode {
    boolean isEnd;
    String word;
    TrieNode[] next = new TrieNode[26];
}

class Trie {
    TrieNode head;
    public Trie(String[] words) {
        head = new TrieNode();
        for (String word : words) {
            addWord(word);
        }
    }

    public boolean addWord(String word) {
        TrieNode curr = head;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int idx = char2idx(ch);
            TrieNode next = curr.next[idx];
            if (next == null) {
                curr.next[idx] = new TrieNode();
            }
            curr = curr.next[idx];
        }
        // handle the last letter
        if (curr.isEnd) {return false;}
        curr.word = new String(word);
        return (curr.isEnd = true);
    }

    public static int char2idx(char ch) {
        return ch - 'a';
    }
}

public class WordSearch {
    public static void main(String[] args) {
        Solution sol = new Solution();
        char[][] board = new char[][] {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String[] words = new String[] {"oath","pea","eat","rain"};
        System.out.println(sol.findWords(board, words));
    }
}