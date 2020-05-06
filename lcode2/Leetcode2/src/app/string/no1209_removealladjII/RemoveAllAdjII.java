package app.string.no1209_removealladjII;

import app.Timer;

/**
 * Given a string s, a k duplicate removal consists of choosing k adjacent and
 * equal letters from s and removing them causing the left and the right side of
 * the deleted substring to concatenate together.
 * 
 * We repeatedly make k duplicate removals on s until we no longer can.
 * 
 * Return the final string after all such duplicate removals have been made.
 * 
 * It is guaranteed that the answer is unique.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: s = "abcd", k = 2 Output: "abcd" Explanation: There's nothing to
 * delete. Example 2:
 * 
 * Input: s = "deeedbbcccbdaa", k = 3 Output: "aa" Explanation: First delete
 * "eee" and "ccc", get "ddbbbdaa" Then delete "bbb", get "dddaa" Finally delete
 * "ddd", get "aa" Example 3:
 * 
 * Input: s = "pbbcggttciiippooaais", k = 2 Output: "ps"
 * 
 * 
 * Constraints:
 * 
 * 1 <= s.length <= 10^5 2 <= k <= 10^4 s only contains lower case English
 * letters.
 */

class Solution {
    // ref
    public String removeDuplicates2(String s, int k) {
        int i = 0, n = s.length(), count[] = new int[n];
        char[] stack = s.toCharArray();
        for (int j = 0; j < n; ++j, ++i) {
            stack[i] = stack[j];
            count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
            if (count[i] == k)
                i -= k;
        }
        return new String(stack, 0, i);
    }

    // de-duplicate when it reaches k
    public String removeDuplicates(String s, int k) {
        // corner case
        if (s == null) {
            return "";
        }

        char[] sArr = s.toCharArray();
        int stackTop = -1;
        for (int i = 0; i < sArr.length;) {
            if (stackTop >= 0 && sArr[stackTop] == sArr[i]) {
                int count = 0;
                // search forward
                while (i + count < sArr.length && sArr[stackTop] == sArr[i + count]) {
                    count++;
                }
                if (count >= k - 1) {
                    // if the element number after i can reach the k
                    // move the pointer i to a new place
                    i += k - 1;
                    // pop
                    stackTop--;
                } else {

                    // search backward if search forward cannot meet the k requirment
                    int backCount = 0;
                    while (stackTop - backCount >= 0 && sArr[stackTop] == sArr[stackTop - backCount]) {
                        backCount++;
                    }
                    if (backCount + count >= k) {
                        // there are k or more than k element to de-duplicate
                        stackTop -= k - count;
                        i += count;
                    } else {
                        // whatever cannot reach the k
                        while (i < sArr.length && sArr[stackTop] == sArr[i]) {
                            sArr[++stackTop] = sArr[i++];
                        }
                    }
                }
            } else {
                sArr[++stackTop] = sArr[i++];
            }
        }

        return new String(sArr, 0, stackTop + 1);
    }
}

public class RemoveAllAdjII {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "deeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaadeeedbbcccbdaa";
        int k = 3;

        // compare
        String res1 = (String) Timer.execute(sol, "removeDuplicates", new Object[] { s, k }, String.class, int.class);
        System.out.println(res1);

        String res2 = (String) Timer.execute(sol, "removeDuplicates2", new Object[] { s, k }, String.class, int.class);
        System.out.println(res2);
    }
}