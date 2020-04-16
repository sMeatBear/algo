package app.string.no678_validparenthesisstr;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    // real dynamic programming
    public boolean checkValidString(String s) {
        /*
         * we assume '(' respresents 1, ')' represents -1, '*' can be 0, 1, -1
         * what we need to know is if the summation of the string s can be 0 !
         * try to maintain the balance
         */
        int lo = 0, hi = lo;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                lo++;
                hi++;
            } else if (s.charAt(i) == ')') {
                lo--;
                hi--;
            } else {
                // asterisk
                lo--;
                hi++;
            }
            lo = lo < 0 ? 0 : lo;
            
            if (hi < 0) {return false;}
        }
        
        return lo == 0;
    }
    // approach 1: failed
    public boolean checkValidString1(String s) {
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
            // each used star cancelled a right parenthesis, so if the stars are regarded as right parenthesis, 
            // it may count as two right parenthesis
            // ** record unused stars
            countStar *= 2;
            while (!stack.isEmpty() && stack.peek() == '*') {
                countStar++;
                stack.pollFirst();
            }

            // ** clean left parenthesis, let left parenthesis replace used stars
            for (int j = 0; j < countStar && !stack.isEmpty();) {
                char top = stack.pollFirst();
                if (top == ')') {
                    return false;
                } else if (top == '*') {
                    // use it as a right parenthesis
                    countStar++;
                } else {
                    j++;
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
        String s = "((*)(*()))((*";
        boolean res = sol.checkValidString(s);
        System.out.println(res);
    }
}