package app.math.lcm;

import app.math.gcd.GreatestCommonDivisor;

public class LeastCommonMultiple {
    public static int getLcm(int a, int b) {
        // sanity check
        if (a == 0 || b == 0) {return 0;}
        
        // 短除法
        int result = 1, divisor = GreatestCommonDivisor.getGcd(a, b);
        while (divisor != 1) {
            result *= divisor;
            a /= divisor;
            b /= divisor;
            divisor = GreatestCommonDivisor.getGcd(a, b);
        }
        result *= a * b;
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getLcm(36, 18));
    }
}