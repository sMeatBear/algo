package app.string.no678_validparenthesisstr;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // approach 1: failed
    public boolean checkValidString(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        
        return isValid(s, 0, s.length());
    }
    
    public boolean isValid(String s, int beginIndex, int endIndex) {
        int len = s.length();
        if (endIndex <= len && beginIndex <= endIndex) {
            Deque<Character> stack = new ArrayDeque<>();
            int i = beginIndex;
            // count used stars
            int countStar = 0;
            
            // use all the stars as left parenthesis first
            while (i < endIndex) {
                if (s.charAt(i) == ')') {
                    Character top = stack.pollFirst();
                    if (top == null) {
                        return false;
                    } else if(top == '*') {
                        countStar++;
                    } else if (top == ')') {
                        return false;
                    }
                } else {
                    stack.offerFirst(s.charAt(i));
                }
                i++;
            }
            // post process
            // ** record unused stars
            while (!stack.isEmpty() && stack.peek() == '*') {
                countStar++;
                stack.pollFirst();
            }

            // ** clean left parenthesis, let left parenthesis replace used stars
            for (int j = 0; j < countStar * 2 && !stack.isEmpty(); j++) {
                char top = stack.pollFirst();
                if (top == ')') {
                    return false;
                } else if (top == '*') {
                    countStar++;
                }
            }
            
            // ** clean stars
            while (!stack.isEmpty()) {
                // cannot put it into the loop control statement (need to return false) !!!
                if (stack.pollFirst() != '*') {
                    return false;
                }
            }
            return stack.isEmpty();
        } else {
            return false;
        }
    }
}

public class ValidParenthesisStr {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "((*)";
        boolean res = sol.checkValidString(s);
        System.out.println(res);
    }
}