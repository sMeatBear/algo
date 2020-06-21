package app.string.no1044_longestdupsubstr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given a string S, consider all duplicated substrings: (contiguous) substrings
 * of S that occur 2 or more times. (The occurrences may overlap.)
 * 
 * Return any duplicated substring that has the longest possible length. (If S
 * does not have a duplicated substring, the answer is "".)
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: "banana" Output: "ana" Example 2:
 * 
 * Input: "abcd" Output: ""
 * 
 * 
 * Note:
 * 
 * 2 <= S.length <= 10^5 S consists of lowercase English letters.
 */
class Solution {
    private static final long q = (1 << 31) - 1;
    private static final long R = 256;
        
    public String longestDupSubstringRef(String S) {      
        int left = 2;
        int right = S.length() - 1;
        int start = 0;
        int maxLen = 0;
        
        while (left <= right) {
            int len = left + (right - left) / 2;
            boolean found = false;
            
            Map<Long, List<Integer>> map = new HashMap<>();
            long hash = hash(S, len);
            map.put(hash, new ArrayList<>());
            map.get(hash).add(0);
            long RM = 1l;
            for (int i = 1; i < len; i++) RM = (R * RM) % q;
            
            loop:
            for (int i = 1; i + len <= S.length(); i++) {
                hash = (hash + q - RM * S.charAt(i - 1) % q) % q;
                hash = (hash * R + S.charAt(i + len - 1)) % q;
                if (!map.containsKey(hash)) {
                    map.put(hash, new ArrayList<>());
                } else {
                    for (int j : map.get(hash)) {
                        if (compare(S, i, j, len)) {
                            found = true;
                            start = i;
                            maxLen = len;
                            break loop;
                        }
                    }
                }
                map.get(hash).add(i);
            }
            if (found) left = len + 1;
            else right = len - 1;
        }
        
        return S.substring(start, start + maxLen);
    }
    
    private long hash(String S, int len) { 
        long h = 0;
        for (int j = 0; j < len; j++) h = (R * h + S.charAt(j)) % q;
        return h;
    }
    
    private boolean compare(String S, int i, int j, int len) {
        for (int count = 0; count < len; count++) {
            if (S.charAt(i++) != S.charAt(j++)) return false ; 
        }
        return true ; 
    }

    // approach 2: (binary search + hashset (rolling hashing)) tc: O(nlogn); sc:
    // O(n) fail ! -> + hashmap
    private int[] powerCache;

    public String longestDupSubstring(String S) {
        char[] sArr = S.toCharArray();
        // the substr len is [0, sArr.len];
        int l = 0, r = sArr.length;
        int maxLen = 0, beginIdx = 0;
        // initialize powercache
        powerCache = new int[sArr.length + 1];
        // lower case english letter
        int base = 26;
        powerCache[0] = 1;
        for (int i = 1; i < powerCache.length; i++) {
            powerCache[i] = base * powerCache[i - 1];
        }

        // binary search the longest substr
        while (l < r) {
            int m = l + (r - l) / 2;
            // check substr with length m (O(n))
            int temp = checkLen(sArr, m);
            if (temp == -1) {
                // we cannot find duplicate substr in this len. So we only shrink the length
                r = m;
            } else {
                l = m + 1;
                // update the maxLen and beginIdx
                maxLen = m;
                beginIdx = temp;
            }
        }
        return S.substring(beginIdx, beginIdx + maxLen);
    }

    // O(n)
    private int checkLen(char[] sArr, int len) {
        // record the <hash, begin index>
        Map<Integer, List<Integer>> record = new HashMap<>();
        int bound = sArr.length - len;
        int prevHash = hash(sArr, 0, len);
        List<Integer> beginIdxs = new ArrayList<>();
        beginIdxs.add(0);
        record.put(prevHash, beginIdxs);
        // i means previous begin index
        for (int i = 0; i < bound; i++) {
            // substract the first and add a new one (sliding window)
            int hash = rollingHash(sArr[i], sArr[i + len], len, prevHash);
            List<Integer> idxList = record.get(hash);
            int currIdx = i + 1;
            if (idxList != null) {
                // handle collision
                for (int begin : idxList) {
                    if (compare(sArr, currIdx, begin, len)) {
                        return currIdx;
                    }
                }
            }
            beginIdxs = new ArrayList<>();
            beginIdxs.add(currIdx);
            record.put(hash, beginIdxs);
            prevHash = hash;
        }
        // no duplicate substr
        return -1;
    }

    /** end is excluded */
    private int hash(char[] sArr, int begin, int len) {
        int hashVal = 0;
        for (int i = len - 1; i >= 0; i--, begin++) {
            hashVal += (sArr[begin] - 'a' + 1) * powerCache[i];
        }
        return hashVal & 0x7fffffff; 
    }

    private int rollingHash(char remove, char add, int len, int hash) {
        return (hash * 26 - (remove - 'a' + 1) * powerCache[len] + (add - 'a' + 1)) & 0x7fffffff;
    }

    private boolean compare(char[] sArr, int i1, int i2, int len) {
        for (int i = 0; i < len; i++) {
            if (sArr[i1++] != sArr[i2++]) {
                return false;
            }
        }
        return true;
    }

    // approach 1: dp (tc: O(n^2), sc: O(n)) -- time limit exceeded
    public String longestDupSubstring1(String S) {
        /*
         * dp[i][j] means the longest len of substring ends with S[i] and S[j] base
         * case: dp[i][j] = 0 if i == j, dp[0][j] = {1 | 0} when j is [1, len - 1] and
         * s[j] == s[0] induction: dp[i][j] = dp[i-1][j-1] + 1 when i == j else 0
         */
        int maxIdx = -1, maxLen = 0;
        char[] sArr = S.toCharArray();
        int[] dp = new int[sArr.length];
        // base case
        for (int j = 1; j < sArr.length; j++) {
            if (sArr[0] == sArr[j]) {
                dp[j] = 1;
                maxIdx = 0;
                maxLen = 1;
            }
        }
        for (int i = 1; i < sArr.length; i++) {
            int[] newDp = new int[sArr.length];
            newDp[i] = 0;
            for (int j = i + 1; j < sArr.length; j++) {
                if (sArr[i] == sArr[j]) {
                    newDp[j] = dp[j - 1] + 1;
                    if (newDp[j] > maxLen) {
                        maxLen = newDp[j];
                        maxIdx = i;
                    }
                }
            }
            dp = newDp;
        }
        return S.substring(maxIdx - maxLen + 1, maxIdx + 1);
    }
}

public class LongestDupSubstr {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String res = sol.longestDupSubstring("banana");
        System.out.println(res);
    }
}