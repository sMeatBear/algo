package app.dp.no139_wordbreak;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation. You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"] Output: true Explanation:
 * Return true because "leetcode" can be segmented as "leet code". Example 2:
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"] Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple". Note that you are allowed to reuse a dictionary word. Example 3:
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
class Solution {
    // approach 2: dp tc: O(n^2)
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        Set<String> dict = new HashSet<>(wordDict);
        
        // dp[i] represents that if there is a combination in s from 0 to i [0, i]
        // O(n^2)
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[len];
    }
    
    // approach 1: recursive (backtracking) stackoverflow
    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        return split(s, 0, s.length(), dict);
    }
    
    public boolean split(String s, int start, int len, Set<String> dict) {
        // previous layer reach the end when find the word in the dict
        if (start == len) {
            return true;
        }
        
        StringBuilder strb = new StringBuilder();
        boolean res = false;
        for (int i = start; i < len; i++) {
            strb.append(s.charAt(i));
            if (dict.contains(strb.toString())) {
                res = split(s, i + 1, len, dict);
                // if it can be splitted like this
                if (res) {
                    return res;
                }
                // else do not split and continue to check
            }
        }
        
        return res;
    }
}
public class WordBreak {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "leetcode";
        boolean res = sol.wordBreak(s, Arrays.asList(new String[] {"leet", "code"}));
        System.out.println(res);
    }
}