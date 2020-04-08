package app.dp.no91_decodeways;

/**
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 */
class Solution {
    // Approach 1: DP
    public int numDecodings(String s) {
        /*
        Thoughts:
            1. Use dp to record the possible decoding ways at each stage
            2. The current state is only relative with previous one dp[i] ~ dp[i-1]
        Conditions:
            1. If current digit can combine with the previous one (10 <= prev * 10 + curr <= 26):
                dp[i] += dp[i - 2]
            2. If current digit can exist alone (curr > 0):
                dp[i] += dp[i - 1]
            3. If current digit is 0 and cannot combine with the previous digit (prev * 10 + curr > 26),
                then it's a wrong code.
        */
        
        // Speical case
        if (s.charAt(0) == '0') return 0;
        
        // dp2: prev prev position dp1: prev position
        int dp2 = 1, dp1 = 1;
        for (int i = 2; i <= s.length(); i++) {
            int twoDigits = Integer.parseInt(s.substring(i - 2, i));
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            // Compute the total decoding ways end at i-1
            int curr = 0;
            
            // Three situations
            if (oneDigit > 0) curr += dp1;
            else if (twoDigits > 26) return 0;
            if (twoDigits > 9 && twoDigits <= 26) curr += dp2;
            
            // Update states of dp2, dp1
            dp2 = dp1;
            dp1 = curr;
        }
        
        return dp1;
    }
}

public class DecodeWays {

}