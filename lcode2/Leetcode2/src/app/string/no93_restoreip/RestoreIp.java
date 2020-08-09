package app.string.no93_restoreip;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.

A valid IP address consists of exactly four integers (each integer is between 0 and 255) separated by single points.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/restore-ip-addresses
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        dfs(s, 0, new int[4], 0, result);
        return result;
    }

    private void dfs(String s, int start, int[] bytes, int byteNo, List<String> result) {
        if (start >= s.length()) {return;}

        // handle the last
        if (byteNo == 3) {
            // ** the end cannot start with 0 if the number of rest digit is not 1
            if (s.charAt(start) == '0' && s.length() - start > 1 || s.length() - start > 3) {return;}
            int lastNum  =  Integer.parseInt(s.substring(start, s.length()));
            if (lastNum <= 255) {
                bytes[3] = lastNum;

                StringBuilder sbd = new StringBuilder();
                for (int b : bytes) {
                    sbd.append(b).append('.');
                }
                result.add(sbd.substring(0, sbd.length() - 1));
            }
            // backtrack
            return;
        }

        // if current digit starts with 0 we can only take 0 as the digit
        if (s.charAt(start) == '0') {
            bytes[byteNo] = 0;
            dfs(s, start + 1, bytes, byteNo + 1, result);
        } else {
            int size = 3;
            for (int i = 1; i <= size; i++) {
                int end = start + i;
                int currByte = -1;
                if (end < s.length() && (currByte = Integer.parseInt(s.substring(start, end))) <= 255) {
                    bytes[byteNo] = currByte;
                    dfs(s, end, bytes, byteNo + 1, result);
                }
            }
        }
    }
}

public class RestoreIp {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "2552551113521212";
        System.out.println(sol.restoreIpAddresses(s));
    }
}