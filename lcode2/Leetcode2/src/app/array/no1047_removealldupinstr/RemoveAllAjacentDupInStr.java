package app.array.no1047_removealldupinstr;

import java.util.ArrayDeque;
import java.util.Deque;

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