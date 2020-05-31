package app.dp.no72_editdistance;

/**
 * 
 * Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')
 */
class Solution {
    // approach 3: dfs
    public int minDistance(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 == null) {
            return word2.length();
        } else if (word2 == null) {
            return word1.length();
        }
        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();
        return dfs(w1, w2, 0, 0);
    }

    // bottom-up
    public int dfs(char[] w1, char[] w2, int w1Index, int w2Index) {
        // base case
        if (w1Index >= w1.length || w2Index >= w2.length) {
            return w1.length - w1Index + w2.length - w2Index;
        }
        int minDis = Integer.MAX_VALUE;
        // same
        if (w1[w1Index] == w2[w2Index]) {
            minDis = dfs(w1, w2, w1Index + 1, w2Index + 1);
        }
        // replace
        minDis = Math.min(minDis, dfs(w1, w2, w1Index + 1, w2Index + 1) + 1);
        // remove (assume we remove the char in the w1Index and use next char in w1 to compare)
        minDis = Math.min(minDis, dfs(w1, w2, w1Index + 1, w2Index) + 1);
        // insert (assume we insert a char into w1Index which is same as the char in w2Index)
        minDis = Math.min(minDis, dfs(w1, w2, w1Index, w2Index + 1) + 1);
        return minDis;
    }

    // approach 2: dp space optimized
    public int minDistance2(String word1, String word2) {
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 == null) {
            return word2.length();
        } else if (word2 == null) {
            return word1.length();
        }
        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();
        // take the shorter string as the dp's len
        if (w1.length < w2.length) {
            // swap, let w2 to be the shorter str
            char[] tmp = w2;
            w2 = w1;
            w1 = tmp;
        }
        int[] dp = new int[w2.length + 1];
        // fill the table
        // base case
        for (int j = 0; j < dp.length; j++) {
            dp[j] = j;
        }
        // induction
        for (int i = 1; i <= w1.length; i++) {
            int[] nextDp = new int[w2.length + 1];
            nextDp[0] = i;
            for (int j = 1; j < dp.length; j++) {
                if (w1[i - 1] == w2[j - 1]) {
                    nextDp[j] = dp[j - 1];
                } else {
                    nextDp[j] = Math.min(nextDp[j - 1], Math.min(dp[j], dp[j - 1])) + 1;
                }
            }
            dp = nextDp;
        }
        return dp[w2.length];
    }
    
    // approach 1: classic dp (tc,sc: O(mn))
    public int minDistance1(String word1, String word2) {
        /*
         * two dim dp:
         * 1. base case:
         *    dp[0][0] = 0
         *    dp[0][j] = j
         *    dp[i][0] = i
         * 2. induction: dp[i][j]： i -- word1's len, j -- word2's len
         *    if word1[i - 1] = word2[j - 1]
         *       dp[i][j] = dp[i-1][j-1]
         *    else
         *       dp[i][j] = min (dp[i-1][j] (delete i), dp[i][j-1] (delete j) 
         *                        , dp[i-1][j-1] (replace)) + 1
         */
        if (word1 == null && word2 == null) {
            return 0;
        } else if (word1 == null) {
            return word2.length();
        } else if (word2 == null) {
            return word1.length();
        }
        char[] w1 = word1.toCharArray(), w2 = word2.toCharArray();
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        // fill the table
        // base case
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        // induction
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (w1[i - 1] == w2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], 
                                 Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[w1.length][w2.length];
    }
}

public class EditDistance {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String word1 = "dinitrophenylhydrazine",
               word2 = "acetylphenylhydrazine";
        System.out.println(sol.minDistance(word1, word2));
    }
}