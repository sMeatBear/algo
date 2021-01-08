package app.math.gcd;

public class GreatestCommonDivisor {
    public static int getGcd(int a, int b) {
        if (a == 0 || b == 0) {return 0;}
        // switch a and b
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }
        
        int mod = 0;
        // 辗转相除法
        while ((mod = a % b) != 0) {
            a = b;
            b = mod;
        }

        return b;
    }

    public static int gcd(int x, int y) {
        if (x < y) {
            int temp = y;
            y = x;
            x = temp;
        }
        return gcdHelper(x, y);
    }

    private static int gcdHelper(int x, int y) {
        return y > 0 ? gcdHelper(y, x % y) : x;
    }

    public static void main(String[] args) {
        System.out.println(gcd(27, 9));
    }
}