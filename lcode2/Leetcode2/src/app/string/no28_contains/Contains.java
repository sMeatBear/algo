package app.string.no28_contains;

/**
 * Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

 

Constraints:

haystack and needle consist only of lowercase English characters.

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/implement-strstr
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    // rabin-carp + sliding windows
    public int strStr(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }
        int base = 131;
        long currBase = 1;
        // compute the needle's hash value
        long needleHash = 0, prevBase = currBase;
        int len = needle.length();
        for (int i = len - 1; i >= 0; i--) {
            needleHash = (needle.charAt(i) * currBase + needleHash);
            prevBase = currBase;
            currBase = (currBase * base);
        }

        currBase = 1;
        // traverse the haystack
        int i = len - 1;
        long hayHash = 0;
        for (; i >= 0; i--) {
            hayHash = (haystack.charAt(i) * currBase + hayHash);
            currBase = (currBase * base);
        }

        currBase = prevBase;
        if (needleHash == hayHash) {
            return 0;
        }
        for (i = len; i < haystack.length(); i++) {
            hayHash = (((hayHash - haystack.charAt(i - len) * currBase) * base + 
                        haystack.charAt(i)));
            if (needleHash == hayHash) {
                return i - len + 1;
            }
        }
        return -1;
    }
}

public class Contains {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String haystack = "missippi", needle = "issip";
        int res = sol.strStr(haystack, needle);
        System.out.println(res);
    }
}