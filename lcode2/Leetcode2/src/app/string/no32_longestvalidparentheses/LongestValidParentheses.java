package app.string.no32_longestvalidparentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/**
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */
class Solution {
    // approach 2:
    public int longestValidParentheses(String s) {
        // start: end of last valid sequence
        // max: max valid sequence length
        int max = 0, cur = 0, start = -1;
        Deque<Integer> stack = new ArrayDeque<>();
        // !! to record left parenthesis index
        // Deque<Integer> indexes = new ArrayDeque<>(); 

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // pop '('
                // End condition which cannot produce continuous valid parentheses
                if (stack.isEmpty()) {
                    start = i;
                } else {
                    stack.pop();
                    // the outmost left parenthesis that can be canceled out
                    // !! if stack is empty then it means find a valid sequence
                    if (stack.isEmpty()) {
                        cur = i - start;
                        max = max > cur ? max : cur;
                    } else {
                        // how many parentheses have been canceled out
                        cur = i - stack.peek();
                        max = max > cur ? max : cur;
                    }
                }
            }
        }

        return max;
    }
    // approach 1: bf
    /**
     * Use a stack to store the left parenthesis. Pop left parenthesis when meet right parenthesis
     */
    public int longestValidParentheses1(String s) {
        // max pair
        int max = 0, cur = 0;
        Deque<Character> stack; 

        for (int j = 0; j < s.length(); j++) {
            stack = new ArrayDeque<>();
            cur = 0;
            for (int i = j; i < s.length(); i++) {
                if (s.charAt(i) == '(') {
                    stack.push('(');
                } else {
                    // pop '('
                    // End condition which cannot produce continuous valid parentheses
                    if (stack.isEmpty()) {
                        cur = 0;
                    } else {
                        stack.pop();
                        // assume it may end as a valid sequence
                        cur++;
                        // !! if stack is empty then it means find a valid sequence
                        if (stack.isEmpty()) max = max > cur ? max : cur;
                    }
                }
            }
        }

        return max * 2;
    }
}


public class LongestValidParentheses {
    public static void main(String[] args) {
        Solution sol = new Solution();
        // !! "()(()"
        // "(()"
        // "(()(()()()"

        String s = "()(()";
        int res = sol.longestValidParentheses(s);
        System.out.println(res);
    }
}