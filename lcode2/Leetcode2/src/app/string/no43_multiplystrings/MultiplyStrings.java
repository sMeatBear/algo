package app.string.no43_multiplystrings;

/**
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2, also represented as a string.
 * 
 * Example 1:
 * 
 * Input: num1 = "2", num2 = "3" Output: "6" Example 2:
 * 
 * Input: num1 = "123", num2 = "456" Output: "56088"
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 110. Both num1 and num2 contain only
 * digits 0-9. Both num1 and num2 do not contain any leading zero, except the
 * number 0 itself. You must not use any built-in BigInteger library or convert
 * the inputs to integer directly.
 */
class Solution {
    // optimized:
    public String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length(), zero = 0;
        int[] a = new int[m], c = new int[m + n];
        for (int i = 0, k = m; i < m; i++)
            a[--k] = num1.charAt(i) - '0'; // reverse the first number
        for (int i = n - 1; i >= 0; i--)
            add(c, a, num2.charAt(i) - '0', zero++); // multiply each digits of num2 to num1
        carry(c); // handle all carry operation together
        int i = m + n;
        while (i > 0 && c[--i] == 0)
            ; // find the highest digit
        i++;
        StringBuilder ret = new StringBuilder(i);
        while (i > 0)
            ret.append((char) (c[--i] + '0'));
        return ret.toString();
    }

    void carry(int[] a) {
        int i;
        for (int k = 0, d = 0; k < a.length; k++) {
            i = a[k] + d;
            a[k] = i % 10;
            d = i / 10;
        }
    }

    void add(int[] c, int[] a, int b, int zero) {
        for (int i = zero, j = 0; j < a.length; j++, i++)
            c[i] += a[j] * b;
    }

    // approach 1:
    public String multiply1(String num1, String num2) {
        // the max size of two integers do the multiplication
        int maxLen = num1.length() + num2.length();
        // store the number of each bit
        int[] res = new int[maxLen], num1int = new int[num1.length()], num2int = new int[num2.length()];

        // convert num1 and num2 to int[] and reverse
        for (int i = 0; i < num1.length(); i++) {
            num1int[i] = num1.charAt(num1.length() - i - 1) - '0';
        }
        for (int i = 0; i < num2.length(); i++) {
            num2int[i] = num2.charAt(num2.length() - i - 1) - '0';
        }

        // do the multiplication
        for (int i = 0; i < num1int.length; i++) {
            // result current place
            int curPlace = maxLen - 1;
            curPlace -= i;
            for (int j = 0; j < num2int.length; j++) {
                // update place
                int place = curPlace - j;
                // multiply
                int tmp = num1int[i] * num2int[j];
                int ones = tmp % 10, carry = tmp / 10;
                int addToRes = res[place] + ones;
                if (addToRes >= 10) {
                    carry++;
                    res[place] = addToRes - 10;
                } else {
                    res[place] = addToRes;
                }
                // update higher place by carry
                // !! if higher place is larger than 10
                place -= 1;
                while (res[place] + carry >= 10) {
                    res[place] = (res[place] + carry) % 10;
                    carry = 1;
                    place--;
                }
                res[place] += carry;
            }
        }

        // convert to string
        StringBuilder resStr = new StringBuilder();
        int i = 0;
        // if the high places is 0
        while (i < res.length && res[i] == 0)
            i++;
        for (; i < res.length; i++) {
            resStr.append(res[i]);
        }
        if (resStr.length() == 0)
            return "0";
        return resStr.toString();
    }
}

public class MultiplyStrings {
    public static void main(String[] args) {
        Solution s = new Solution();
        String num1 = "96", num2 = "313";
        String res = s.multiply(num1, num2);
        System.out.println(res);
    }
}