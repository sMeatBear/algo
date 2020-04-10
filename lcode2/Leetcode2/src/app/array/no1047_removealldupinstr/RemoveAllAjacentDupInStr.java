package app.array.no1047_removealldupinstr;

import java.util.ArrayDeque;
import java.util.Deque;

/**
Given a string S of lowercase letters, a duplicate removal consists of choosing two adjacent and equal letters, and removing them.

We repeatedly make duplicate removals on S until we no longer can.

Return the final string after all such duplicate removals have been made.  It is guaranteed the answer is unique.

 

Example 1:

Input: "abbaca"
Output: "ca"
Explanation: 
For example, in "abbaca" we could remove "bb" since the letters are adjacent and equal, and this is the only possible move.  The result of this move is that the string is "aaca", of which only "aa" is possible, so the final string is "ca".
 

Note:

1 <= S.length <= 20000
S consists only of English lowercase letters.
 */
class Solution {
    // approach 2: two pointers
    public String removeDuplicates(String S) {
        // corner case
        if (S == null || S.length() == 0) return "";
        
        // end of the non duplicate position + 1
        int end = 0;
        char[] strArr = S.toCharArray();
        for (int i = 0; i < strArr.length; i++, end++) {
            strArr[end] = strArr[i];
            // use end index to check if there is duplicate at the end
            if (end > 0 && strArr[end - 1] == strArr[end]) {
                end -= 2;
            }
        }
        
        return new String(strArr, 0, end);
    }
    
    // approach 1: stack tc: O(n) sc: O(n)
    public String removeDuplicates1(String S) {
        // corner case
        if (S == null || S.length() == 0) return "";
        
        // use a stack
        Deque<Character> stack = new ArrayDeque<>();
        stack.push(S.charAt(0));
        
        for (int i = 1; i < S.length(); i++) {
            if (!stack.isEmpty() && stack.peek() == S.charAt(i)) {
                stack.pop();
            } else {
                stack.push(S.charAt(i));
            }
        }
        
        StringBuilder sb = new StringBuilder(); 
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        
        return sb.toString();
    }
}

public class RemoveAllAjacentDupInStr {
    public static void main(String[] args) {
        String S = "abbaca";
        Solution s = new Solution();
        System.out.println(s.removeDuplicates(S));
    }
}