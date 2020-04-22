package app.dp.no140_wordbreakII;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is a
 * valid dictionary word. Return all such possible sentences.
 * 
 * Note:
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation. You may assume the dictionary does not contain duplicate words.
 * Example 1:
 * 
 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output: [ "cats and dog", "cat sand dog" ] Example 2:
 * 
 * Input: s = "pineapplepenapple" wordDict = ["apple", "pen", "applepen",
 * "pine", "pineapple"] Output: [ "pine apple pen apple", "pineapple pen apple",
 * "pine applepen apple" ] Explanation: Note that you are allowed to reuse a
 * dictionary word. Example 3:
 * 
 * Input: s = "catsandog" wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: []
 */

class Solution {
    // approach 2: backtrack (Time Limit Exceeded)
    public List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<String>();
        wordBreakUtil(s, wordDict, result, new StringBuilder());
        return result;
    }

    public void wordBreakUtil(String s, List<String> wordDict, List<String> result, StringBuilder sb) {
        if (s.equals("")) {
            result.add(sb.toString().trim());
            return;
        }

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                // once find the match, 
                // copy current sequence as a possible comb and pass it to next
                StringBuilder res = new StringBuilder(sb);
                res.append(" ").append(word);
                wordBreakUtil(s.substring(word.length()), wordDict, result, res);
            }
        }
    }

    // approach 1: dp + backtrack
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dictSet = new HashSet<>();
        List<String> res = new ArrayList<>();

        // use a hashset to store words
        for (String word : wordDict) {
            dictSet.add(word);
        }

        // record each stages previous separation index
        List<List<Integer>> dp = new ArrayList<>();
        dp.add(null);

        for (int i = 1; i <= s.length(); i++) {
            List<Integer> curr = null;
            // handle j = 0
            if (dictSet.contains(s.substring(0, i))) {
                curr = new ArrayList<>();
                curr.add(0);
            }
            for (int j = 1; j < i; j++) {
                // if there is a combination from [0, j) and [j, i) is a word in the dict
                if (dp.get(j) != null && dictSet.contains(s.substring(j, i))) {
                    curr = curr == null ? new ArrayList<>() : curr;
                    // add this j to curr list
                    // which means we can find a possible combination seperate by j
                    curr.add(j);
                }
            }

            dp.add(curr);
        }

        // dfs traverse the previous index from behind
        int len = s.length();
        String[][] cache = new String[len][len + 1];
        Deque<String> strStack = new ArrayDeque<>();
        List<Integer> prevIdx = dp.get(len);
        if (prevIdx != null) {
            dfs(res, cache, strStack, prevIdx, len, s, dp);
        }

        return res;
    }

    private void dfs(List<String> res, String[][] cache, Deque<String> strStack, List<Integer> prevIdx, int lastIdx,
            String s, List<List<Integer>> dp) {
        for (int prev : prevIdx) {
            if (cache[prev][lastIdx] == null) {
                cache[prev][lastIdx] = s.substring(prev, lastIdx);
            }

            String subStr = cache[prev][lastIdx];
            if (prev == 0) {
                StringBuilder sb = new StringBuilder(subStr);
                for (String sub : strStack) {
                    sb.append(sub);
                }
                res.add(sb.toString());
            } else {
                strStack.push(subStr);
                strStack.push(" ");
                dfs(res, cache, strStack, dp.get(prev), prev, s, dp);
                // backtrack
                strStack.pop();
                strStack.pop();
            }
        }
    }
}

public class WordBreakII {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "catsanddog";
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        List<String> res = sol.wordBreak2(s, wordDict);
        System.out.println(res);
    }
}