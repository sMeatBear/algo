package app.math.no171_excelsheetcolnum;

/**
Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701
 */

class Solution {
    // Actually it's a base problem (26 base)
    public int titleToNumber(String s) {
        int base = 26, res = 0;
        for (int i = 0; i < s.length(); i++) {
            int pos = s.length() - 1 - i;
            int digit = s.charAt(pos) - 'A' + 1;
            res += Math.pow(base, i) * digit;
        }

        return res;
    }
}

public class ExcelSheetColNum {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "ZY";
        int res = sol.titleToNumber(s);
        System.out.println(res);
    }
}