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

    public static void main(String[] args) {
        System.out.println(getGcd(27, 9));
    }
}