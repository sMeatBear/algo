package app.dp.no392_issubsequence;

/**
Given a string s and a string t, check if s is subsequence of t.

You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

Example 1:
s = "abc", t = "ahbgdc"

Return true.

Example 2:
s = "axc", t = "ahbgdc"

Return false.

Follow up:
If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 */
class Solution {
    // Ver 3
    public boolean isSubsequence(String s, String t) {
        // Traverse the t, and find all char in s in order
        int j = -1;
        
        for (int i = 0; i < s.length(); i++) {
            j = t.indexOf(s.charAt(i), j + 1);
            if (j == -1) return false;
        }

        // If we can traverse the whole String s
        return true;
    }
    // Ver 2
    public boolean isSubsequence2(String s, String t) {
        if (s.length() == 0) return true;
        // Traverse the t, and find all char in s in order
        int j = 0;
        char curr = s.charAt(j);
        
        for (int i = 0; i < t.length(); i++) {
            if (curr == t.charAt(i)) {
                if (++j >= s.length()) break;
                curr = s.charAt(j);
            }
        }

        // If we can traverse the whole String s
        return j == s.length();
    }
    
    // Ver 1
    public boolean isSubsequence1(String s, String t) {
        // Traverse the t, and find all char in s in order
        int j = 0;
        
        for (int i = 0; i < t.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
            }
        }

        // If we can traverse the whole String s
        return j == s.length();
    }
}

public class IsSubsequence {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aed", t = "abcde";
        boolean res = sol.isSubsequence(s, t);
        System.out.println(res);
    }
}