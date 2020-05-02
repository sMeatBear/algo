package app.math;

import java.util.HashMap;
import java.util.Map;

public class MyMath {
    /**
     * Compute the greatest common divisor
     */
    public static int gcd(int x, int y) {
        for (int r; (r = x % y) != 0; x = y, y = r) {}
        return y;
    }


    // approach 3:
    public double myPow(double x, int shittyN) { 
        long n = shittyN;
        if (x == 0 || x == 1) {
            return x;
        }
        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        }
        if(n == 0) return 1;
        if(n < 0) {
            n = -n;
            x = 1 / x;
        }
        double ans = 1;
        while(n > 0){
            if(n % 2 == 1) ans *= x;
            x *= x;
            n >>= 1;
        }
        return ans;
    }
    
    // iterative
    public static double powItr(double x, int shittyN) {
        long n = shittyN;
        if (n == 0) {
            return 1;
        }
        // corner case:
        if(x == 0 || x == 1) {
            return x;
        }
        if (x == -1) {
            return Math.abs(n) % 2 == 0 ? 1 : -1;
        }

        if (n < 0) {
            x = 1.0 / x;
            n = -n;
        }
        Map<Integer, Double> cache = new HashMap<>();
        cache.put(1, x);
        double res = x;
        for (int pow = 1, tmpPow = pow; pow < n;) {
            if ((long)pow + tmpPow <= n) {
                // double the power
                res *= x;
                x = res;
                pow += tmpPow;
                tmpPow = pow;
                cache.put(pow, res);
            } else {
                while ((long)pow + tmpPow > n) {
                    tmpPow >>>= 1;
                }
                x = cache.get(tmpPow);
                res *= x;
                pow += tmpPow;
            }
            if (res == 0) {
                return res;
            }
        }
        
        return res;
    }
    
    public static double pow(double x, int n) {
        // corner case
        if (x == 0 || x == 1) {
            return x;
        }
        if (x == -1) {
            return n % 2 == 0 ? 1 : -1;
        } 

        if (n == 0) {
            return 1;
        }
        if (n > 0) {
            return pow(x, n - 1);
        }
        return 1.0 / x * pow(x, n + 1);
    }

    public static void main(String[] args) {
        // int gcd = gcd(10, 124);
        // System.out.println(gcd);
        double res = pow(2, -13);
        System.out.println(res == Math.pow(2, -13));
    }
}