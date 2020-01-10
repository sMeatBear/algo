package app.basic.no29_dividetwointegers;

class Solution {
    // approach 1:
    public int divide1(int dend, int dsor) {
        long dividend = dend, divisor = dsor;
        // check if dividend is 0
        if (dividend == 0)
            return 0;
        // special case
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // if dividend and divisor have different sign
        boolean differentSign = false;
        if (dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0) {
            differentSign = true;
        }
        dividend = dividend < 0 ? -dividend : dividend;
        divisor = divisor < 0 ? -divisor : divisor;

        long quotient = 0, sum = 0;
        while (sum <= dividend) {
            sum += divisor;
            quotient++;
            // double the sum
            long tmp;
            if ((tmp = sum + sum) <= dividend) {
                sum = tmp;
                quotient += quotient;
            }
        }

        if (differentSign)
            return -(int) (quotient - 1);
        return (int) (quotient - 1);
    }

    // approach 2:
    public int divide2(int dividend, int divisor) {
        // check if negative answer
        boolean isNeg = (dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0);

        // use long to take care of overflow
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);

        long res = 0, curr = 1;
        long sub = ldivisor;

        while (ldividend >= ldivisor) {
            if (ldividend >= sub) {
                res += curr;
                ldividend -= sub;
                sub = sub << 1; // sub = sub * 2
                curr = curr << 1; // curr = curr * 2
            } else {
                sub = sub >> 1;
                curr = curr >> 1;
            }
        }

        res = isNeg ? -res : res;

        return (int) (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE ? Integer.MAX_VALUE : res);
    }

    // approach 3:
    public int divide(int A, int B) {
        // one condition that will let the result overflow:
        // A = -2^31, B = -1, A / B = 2^31 which cause overflow
        if (A == 1 << 31 && B == -1) return (1 << 31) - 1;

        // Convert it to positve number to handle:
        // Note that if a = -2^31, the abs result won't change. That's fine because we regard it as a positive number and
        // only do the bitwise operations 
        int a = Math.abs(A), b = Math.abs(B), res = 0;
        for (int x = 31; x >= 0; x--) {
            // >>> unsigned right shift
            // a keeps being divided by 2 until it's smaller than b
            // x is decreaing, keep finding next max partial quotient
            /*
                e.g. a = 13 (1101), b = 3 (11):
                1. Meet the conidition first time: x = 2, which means a(13) / 2 / 2 >= b(3)
                    then we add / 2 / 2 which is / 4 (1 << 2) to the total quotient,
                    then we substract the divisor * partial quotient from the dividend. (a -= b << x)
                2. Now, a = 13 - 3 << 2 = 1, a is always smaller than b now. So the result will be 4
            */
            if ((a >>> x) - b >= 0) {
                // 1 << x is the quotient:
                // 1 << 0 = 1, 1 << 1 = 2, 1 << 2 = 4, 1 << 3 = 8
                res += 1 << x;
                // remove the divisor * part of the quotient
                a -= b << x;
            }
        }
        
        return (A > 0) == (B > 0) ? res : -res;
    }
}

public class DivideTwoIntegers {
    public static void main(String[] args) {
        Solution s = new Solution();
        int quotient = s.divide(10, 3);
        System.out.println(quotient);
        System.out.println(Math.abs(Integer.MIN_VALUE) > 0);
    }
}