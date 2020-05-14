package app.math.no402_removekdigits;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Given a non-negative integer num represented as a string, remove k digits
 * from the number so that the new number is the smallest possible.
 * 
 * Note: The length of num is less than 10002 and will be ≥ k. The given num
 * does not contain any leading zero. Example 1:
 * 
 * Input: num = "1432219", k = 3 Output: "1219" Explanation: Remove the three
 * digits 4, 3, and 2 to form the new number 1219 which is the smallest. Example
 * 2:
 * 
 * Input: num = "10200", k = 1 Output: "200" Explanation: Remove the leading 1
 * and the number is 200. Note that the output must not contain leading zeroes.
 * Example 3:
 * 
 * Input: num = "10", k = 2 Output: "0" Explanation: Remove all the digits from
 * the number and it is left with nothing which is 0. Accepted 114,877
 * Submissions 412,023
 */

class Solution {
    // approach 3: use a implicit stack
    public String removeKdigits(String num, int k) {
        // core: keep removing first non-descending element
        // initialize stack
        char[] stack = new char[num.length()];
        int top = -1;
        for (int i = 0; i < num.length(); i++) {
            char curr = num.charAt(i);
            // keep removing first non-descending element
            // if elements that needed to be removed all stay at the end, 
            // we do the post-process later !
            while (top >= 0 && stack[top] > curr && k > 0) {
                top--;
                k--;
            }
            stack[++top] = curr;
        }

        // post-processing: skip all the zero and reduce the top
        int offset = 0;
        while (offset < stack.length && stack[offset] == '0') {
            offset++;
        }
        int count = top - k + 1 - offset;
        if (offset >= stack.length || count <= 0) {
            return "0";
        }
        return new String(stack, offset, count);
    }

    // approach 2: bf, handle string directly
    public String removeKdigits2(String num, int k) {
        // corner case
        if (k >= num.length()) {
            return "0";
        }
        // initialize number list
        LinkedList<Character> numList = new LinkedList<>();
        for (int i = 0; i < num.length(); i++) {
            numList.add(num.charAt(i));
        }

        for (int i = 0; i < k; i++) {
            // find the last non-descendant element and remove it
            ListIterator<Character> numListItr = numList.listIterator();
            Character prev = null;
            while (numListItr.hasNext()) {
                char curr = numListItr.next();
                // what the fuck with this stupid api ??????????????????????????????
                if (prev != null && prev > curr) {
                    // current pointer will point to curr list node
                    numListItr.previous();
                    // previous node
                    numListItr.previous();
                    numListItr.remove();
                    break;
                }
                prev = curr;
            }
            // if it doesn't have next (reach the end)
            if (!numListItr.hasNext()) {
                numListItr.previous();
                numListItr.remove();
            }
            // remove zero at the front
            while (!numList.isEmpty() && numList.peek() == '0') {
                numList.pop();
            }

        }

        // construct result
        StringBuilder sb = new StringBuilder();
        if (numList.isEmpty()) {
            return "0";
        } else {
            for (char ch : numList) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    // approach 1: bf -> wrong
    public String removeKdigits1(String num, int k) {
        int numInt = Integer.parseInt(num);
        int min = numInt;

        for (int i = 0; i < k; i++) {
            // remove the one digit
            min = Math.min(min, numInt / 10);
            int bound = getLen(numInt);
            for (int j = 1, mask = 100; j < bound; j++, mask *= 10) {
                // remove each digit
                // separate front part and combine rest part
                int mask2 = mask / 10;
                int temp = numInt / mask * (mask2) + numInt % mask2;
                // update min
                min = Math.min(min, temp);
            }
            // another round
            numInt = min;
        }

        return "" + min;
    }

    public int getLen(int num) {
        int len = 0;
        for (; num != 0; num /= 10, len++) {}
        return len;
    }
}

public class RemoveKDigits {
    public static void main(String[] args) {
        int k = 1;
        String num = "10";
        Solution sol = new Solution();
        char[] test = new char[] {'f', 'u', 'c', 'k', 'y', 'o','u'};
        System.out.println(new String(test, 1, 3));
    }
}