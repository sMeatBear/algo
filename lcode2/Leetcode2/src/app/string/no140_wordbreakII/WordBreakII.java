package app.string.no140_wordbreakII;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]
 */
class Solution {
    // approach 1: dp + backtracking
    public List<String> wordBreak(String s, List<String> wordDict) {
        /*
         * dp[i]: string s end at ith char, the combination of the word (list of the string's end idx (not included))
         * base case:
         *   1. dp[i] = null, if i < the length of shortest word in the wordDict
         * induction:
         *   2. dp[i] = list of prev index if dp[i - j] is not null (** i is not included)
         * tc: O(n^2 * AVL) n is the length of s, AVL is the average length of the eahc word
         */
        // sanity check
        if (s == null || s.length() == 0 || wordDict.size() == 0) {return new ArrayList<>();}
        
        // find the len of shortest word
        int minLen = Integer.MAX_VALUE;
        Set<String> wordSet = new HashSet<>();
        int i = 0;
        for (String word : wordDict) {
            minLen = Math.min(word.length(), minLen);
            wordSet.add(word);
        }
        
        List[] dp = new ArrayList[s.length() + 1];
        String substr = null;

        // induction
        for (i = minLen; i <= s.length(); i++) {
            // if contains the whole word
            substr = s.substring(0, i);
            if (wordSet.contains(substr)) {
                dp[i] = new ArrayList();
                dp[i].add(0);
            }
            for (int j = i - 1; j >= minLen; j--) {
                substr = s.substring(j, i);
                // can be appended
                if (wordSet.contains(substr) && dp[j] != null) {
                    if (dp[i] == null) {dp[i] = new ArrayList();}
                    dp[i].add(j);
                }
            }
        }
        
        // form the result
        List<String> result = new ArrayList<>();
        if (dp[s.length()] == null) {return result;}
        // backtracking
        appendResult(result, dp, new ArrayDeque<>(), s.length(), s);

        return result;
    }

    private void appendResult(List<String> result, List[] dp, Deque<String> oneRes, int i, String s) {
        // base case
        if (i == 0) {
            StringBuilder sbd = new StringBuilder();
            for (String word : oneRes) {
                sbd.append(word).append(' ');
            }
            result.add(sbd.substring(0, sbd.length() - 1));
            return;
        }

        // dfs part
        for (Object obj : dp[i]) {
            int begin = (int) obj;
            oneRes.push(s.substring(begin, i));
            // dfs
            appendResult(result, dp, oneRes, begin, s);
            // backtrack
            oneRes.pop();
        }
    }
}

public class WordBreakII {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "pineapplepenapple";
        List<String> wordDict = Arrays.asList(new String[] {"apple","pen","applepen","pine","pineapple"});
        System.out.println(sol.wordBreak(s, wordDict));
    }
}